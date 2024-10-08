package HR.Bussiness;

import java.time.LocalDate;
import java.util.*;

public class Shift {
    private LocalDate date;
    //tells if the shift is evening or morning, we will use only 1 \ 2 from the enum
    private ShiftTime shift_time;
    private double start;
    private double end;
    //all the Workers in the shift
    //<Job , worker>
    private Map<Jobs,List<Worker>> WorkerList;


    public Shift(LocalDate date,ShiftTime ShiftTime,double start,double end,Worker manager){
        this.date=date;
        this.shift_time=ShiftTime;
        WorkerList=new HashMap<>();
        this.start=start;
        this.end=end;
        //we also add the manager to the list
        this.AddWorker(Jobs.ShiftManager,manager);
    }
    //builder for empty shift
    public Shift(LocalDate date,ShiftTime ShiftTime,double start,double end){
        this.date=date;
        this.shift_time=ShiftTime;
        WorkerList=new HashMap<>();
        this.start=start;
        this.end=end;
    }



    public void AddWorker(Jobs job, Worker worker){
        //if not exists yet we ceate first
        if ( this.WorkerList.get(job)==null){
            this.WorkerList.put(job,new ArrayList<>());
        }
        this.WorkerList.get(job).add(worker);
    }

    public void RemoveWorker(String ID){
        //remember the role of the worker deleted
        Jobs jobremove=Jobs.Cashier;
        //we changed it to true if we deleted someone
        boolean changed=false;
        for(Jobs job:WorkerList.keySet()){
            for (Worker worker:WorkerList.get(job)){
                if(worker.getID().equals(ID)){
                    WorkerList.get(job).remove(worker);
                    //if empty after deletion we remove the list and value
                    jobremove=job;
                    changed=true;
                    break;
                }
                //if we deleted someone were done
                if (changed){
                    break;
                }
            }
        }
        //if no one as this role we delete it from the list in the shift
        if(changed && WorkerList.get(jobremove).size()==0){
            //if he deletes the shift manager we tell him the shift will be empty
            if(jobremove==Jobs.ShiftManager){
                System.out.println("you removed the shift manager so the shift will be canceled");
            }
            WorkerList.remove(jobremove);
        }
    }

    public boolean IsWorkerAtShift(String ID){
        for(Jobs job:WorkerList.keySet()){
            for (Worker worker:WorkerList.get(job)){
                if(worker.getID().equals(ID)){
                    return true;
                }
            }
        }
        return false;
    }

    //check if the shift is empty
    public boolean IsEmptyShift(){
        if (WorkerList.get(Jobs.ShiftManager)==null){
            return true;
        }
        return false;
    }

    public Map<Jobs, List<Worker>> getWorkerList() {
        return WorkerList;
    }

    public LocalDate getDate() {
        return date;
    }

    public ShiftTime getShift_time() {
        return shift_time;
    }

    public double getStart() {
        return start;
    }

    public double getEnd() {
        return end;
    }


    //if the weekly is canceled in the middle we empty the shift and update each worker
    public void clearWorkers(){
        LocalDate today =this.date;
        String dayName = today.getDayOfWeek().toString().charAt(0)+today.getDayOfWeek().toString().substring(1).toLowerCase();
        Days day=Days.valueOf(dayName);
        for(Jobs job:WorkerList.keySet()){
            for(Worker worker: WorkerList.get(job)){
                worker.RemoveShift(day);
            }
        }
    }
    public void PrintMe(){
        //if its an empty shift we just print that its empty
        if(this.IsEmptyShift()){
            System.out.println("at "+this.date + " " + this.shift_time + " at: "+this.date.getDayOfWeek() +" there is no shift ");
        }
        else {
            System.out.println(this.date +" at: "+this.date.getDayOfWeek() +" "+ this.shift_time + "\n" +
                    "from: " + this.start + " until: " + this.end);
            for (Jobs job : this.WorkerList.keySet()) {
                if(WorkerList.get(job).size()!=0){
                    System.out.println("as "+job+" the Workers are:");
                    for (Worker worker: WorkerList.get(job)){
                        worker.Printme();
                    }
                    System.out.println();
                }

            }
        }
    }
}
