package HR.DataAccess;
import HR.Bussiness.*;
import DBConnect.Connect;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

//this will be singleton
public class DataController {
    private static DataController instance;
    private WorkerMapper workerMapper;
    private WeeklyMapper weeklyMapper;
    private SuperMapper superMapper;
    private CashRegisterMapper cashRegisterMapper;
    private ManagerPasswordMapper managerPasswordMapper;

    private DataController() {
        cashRegisterMapper=CashRegisterMapper.getInstance();
        superMapper=SuperMapper.getInstance();
        workerMapper=WorkerMapper.getInstance();
        weeklyMapper=WeeklyMapper.getInstance();
        managerPasswordMapper=ManagerPasswordMapper.getInstance();
    }
    public static DataController getInstance() {
        if (instance == null)
            instance = new DataController();
        return instance;
    }
    public Map<String, Worker> getWorkerMap() {
        return workerMapper.getWorkerMap();
    }
    public Map<String, Driver> getDriverMap() {
        return workerMapper.getDriverMap();
    }
    public String getManagerPassword() {
        return managerPasswordMapper.getManagerPassword();
    }
    public void setManagerPassword(String managerPassword) {
        managerPasswordMapper.setManagerPassword(managerPassword);
    }
    public Map<String,Super>getSuperMap(){return superMapper.getSuperMap();
    }
    public Weekly getWeekly(String Branch,String StartDate) {
        return weeklyMapper.getWeekly(Branch,StartDate);
    }
    public void deleteWorkerFromBranch(String ID, String branch){
        workerMapper.deleteWorkerFromBranch(ID,branch);
    }
    public void deleteWorker(String ID){
        workerMapper.deleteWorker(ID);
    }
    public void ReadCancellations(String BranchName,int year,int month,int day){
        cashRegisterMapper.ReadCancellations(BranchName,year,month,day);
    }
    /**
     * @param BranchName a branch name
     * this function will tell the mapper of workers to get all the workers of a super
     */
    public void loadAllWorkersFromSuper(String BranchName){
        workerMapper.ReadAllWorkersFromSuper(BranchName);
    }
    /**
     * this function will tell the mapper of workers to get all the workers
     */
    public void loadAllWorkers(){
        workerMapper.ReadAllWorkers();
    }
    /**
     * @param ID id of worker
     * @return the worker asked if he is in the db
     */
    public Worker getWorker(String ID){
        return workerMapper.getWorker(ID);
    }
    public Driver getDriver(String ID){
        return workerMapper.getDriver(ID);
    }
    public void ReadAllDriversByInfo(char licence,Training ability){
        workerMapper.ReadAllDriversByInfo(licence,ability);
    }
    public void DeleteConstraint(String ID){
        workerMapper.DeleteConstraints(ID);
    }
    public void DeleteWorkingDaysAndShifts(String ID, Shift shift){
        weeklyMapper.DeleteWorkerFromShift(ID,shift);
        workerMapper.DeleteWorkingDays(ID);
    }
    public void DeleteWorkingDays(String ID){
        workerMapper.DeleteWorkingDays(ID);
    }
    public void DeleteWorkingDays(String ID, int day){
        workerMapper.DeleteWorkingDays(ID,day);
    }
    public Super getSuper(String name){
        return superMapper.getsuper(name);
    }
    public List<List<String>> getSupers(){return superMapper.readSupers();}
    public void getAllSupers(){superMapper.readAllSupers();}

    public void deleteBranch(String branch){
        superMapper.deleteBranch(branch);
    }

        /**
         * we will save all the changes when we go out of the system
         *
         */
    public void saveData() {
        //todo add writing for the drivers shifts
        workerMapper.WriteAllWorkers();
        workerMapper.WriteAllDrivers();
        cashRegisterMapper.WriteAllCancellations();
        superMapper.WriteAllSupers();
        //write all weekly to db
        weeklyMapper.WriteAllWeekly();
        //close the connection to database when finished
        Connect.disconnect();
    }


}
