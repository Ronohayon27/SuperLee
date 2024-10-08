package HR.Bussiness;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Constraints {

    //a dictionary of a day and the info of the shift he can work there

    private Map<Days, List<CantWork>> cantWork;

    public Constraints(){
        cantWork=new HashMap<>();
    }

    public boolean AddCantWork(Days day,double s,double e,String r ){
        if(s>e){
            return false;
        }
        List<CantWork> curr=cantWork.get(day);
        //if its null we need to add a new one
        if (curr==null){
            curr=new ArrayList<>();
            curr.add(new CantWork(s,e,r));
            cantWork.put(day,curr);
            return true;
        }
        // if not null we add a value to what we already have
        else{
            curr.add(new CantWork(s,e,r));
            return true;
        }
    }

    public Map<Days, List<CantWork>> getCantWork() {
        return cantWork;
    }

    public boolean RemoveCantWork(Days day, double s, double e){
        List<CantWork> curr=cantWork.get(day);
        int i=0;
        //a flag that will tell if we need to delete something
        boolean check=false;
        //if its null we need to add a new one
        if (curr==null){
            return false;
            //we dont need to do anything if not existing
        }
        // if not null we check if we have a time like he mentioned
        else{
            for (i=0;i<curr.size();i++){
                //if we find the right listing we need to delete it
                if(curr.get(i).getStart()==s &&curr.get(i).getEnd()==e){
                    check=true;
                    break;
                }
            }
            //we remove the one we found
            if (check) {
                curr.remove(i);
                //if its empty now we won show this
                if(curr.size()==0){
                    cantWork.remove(day);
                }
                return true;
            }
        }
        return false;
    }

    //function that checks if the day and time of the shift and tells if he can work there
    public boolean CanWork(Days day, double s, double e){
        List<CantWork> curr=cantWork.get(day);
        //if i dont find anything he can work there
        if (curr==null){
            return true;
        }
        //if i find something i need to check if he has anything at the time of the shift
        else{
            for (int i=0;i<curr.size();i++){
                if ((curr.get(i).getStart()<e && curr.get(i).getEnd()>e) || (curr.get(i).getStart()<s && curr.get(i).getEnd()>s)){
                    return false;
                }
            }
        }
        return true;
    }


    //function to print Domain.Constraints - updated 29.3
    public void PrintMe(){
        for (Days day:Days.values()){
            System.out.println(day.toString() +":");
            if(cantWork.get(day) == null || cantWork.get(day).size()==0){
                System.out.println("there are no Constraintss in this day");
                continue;
            }
            for (int i=0;i<cantWork.get(day).size();i++){
                cantWork.get(day).get(i).printMe();
            }
        }
    }

}
