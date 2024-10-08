package HR.Bussiness;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class AWorker {
    protected String Name;
    protected String  ID;
    protected int Bank;
    //might be a txt file in the future
    protected String Contract;
    protected LocalDate StartDate;
    //so we know how much to pay him
    protected double Wage;
    protected double Bonus;
    protected String Password;
    //the shifts he can work at
    protected Constraints ShiftsCantWork;
    //saves the number of days that he worked so we can pay him
    protected int ShiftWorked;
    //saves the days of the week that he is already working
    protected List<Days> WeeklyWorkingDays;
    public AWorker(String ID,String Name,  int Bank,String Contract, double Wage , String Password){
        this.Name=Name;
        this.ID=ID;
        this.Bank=Bank;
        this.Contract=Contract;
        this.Wage=Wage;
        this.Password=Password;
        //puts the date if starting the job
        this.StartDate=LocalDate.now();
        this.ShiftWorked=0;
        this.Bonus = 0;
        //sets the Domain.Constraints to everyday until he updates it
        this.ShiftsCantWork= new Constraints();
        this.WeeklyWorkingDays=new ArrayList<>();
    }
    //builder for stuff from db
    public AWorker(String ID,String Name,  int Bank,String Contract, double Wage , String Password, LocalDate startDate,double bonus,int shiftworked){
        this.Name=Name;
        this.ID=ID;
        this.Bank=Bank;
        this.Contract=Contract;
        this.Wage=Wage;
        this.Password=Password;
        //puts the date if starting the job
        this.StartDate=startDate;
        this.ShiftWorked=shiftworked;
        this.Bonus = bonus;
        //sets the Domain.Constraints to everyday until he updates it
        this.ShiftsCantWork= new Constraints();
        this.WeeklyWorkingDays=new ArrayList<>();
    }

    //at the end of the week we need to put 0 here
    //probably when we start a new scheduel
    public void ResetDaysOfWork(){
        this.WeeklyWorkingDays.clear();
    }
    // reset the number of shifts the worker works in a month
    public void ResetShiftsAmount(){
        this.ShiftWorked = 0;
    }

    //called when we create a new Domain.Constraints
    public boolean AddCantWork(Days day,double s,double e,String r){
        return ShiftsCantWork.AddCantWork(day,s,e,r);
    }
    //called when we delete an Domain.Constraints
    public boolean RemoveCantWork(Days day,double s,double e){
        return ShiftsCantWork.RemoveCantWork(day,s,e);
    }

    //check if entered right password
    public boolean CheckPassword(String Password){
        return this.Password.equals(Password);
    }
    //used to change password
    public void SetPassword(String Password){
        this.Password=Password;
    }

    public boolean IsFree(Days day,double s,double e){
        if(this.WeeklyWorkingDays.size()==6 || this.WeeklyWorkingDays.contains(day)){
            return false;
        }
        //return if he can work there or not
        return ShiftsCantWork.CanWork(day, s, e);
    }

    public String getName(){
        return this.Name;
    }

    public String getID(){
        return this.ID;
    }

    //update a worker that he got a shift
    public void AddShift(Days day){
        this.WeeklyWorkingDays.add(day);
    }
    //update a worker that he got a shift
    public void AddShiftWorked(){
        this.ShiftWorked++;
    }
    //update a worker that he removes a shift - added 31.3
    public void RemoveShift(Days day){
        this.ShiftWorked--;
        this.WeeklyWorkingDays.remove(day);
    }

    // used to change worker name
    public void SetName(String newName){
        this.Name=newName;
    }
    // used to change worker bank details
    public void SetBank(int newBank){
        this.Bank=newBank;
    }

    public int getBank() {
        return Bank;
    }

    public String getContract() {
        return Contract;
    }

    public LocalDate getStartDate() {
        return StartDate;
    }

    public double getBonus() {
        return Bonus;
    }

    public String getPassword() {
        return Password;
    }

    public void ShowConstraints(){
        this.ShiftsCantWork.PrintMe();
    }

   //returns the map of constraints for this worker
    public Map<Days, List<CantWork>> getShiftsCantWork() {
        return ShiftsCantWork.getCantWork();
    }

    public void setWage(double wage){this.Wage = wage;}

    public void addBonus(double bonus){this.Bonus+=bonus;}

    public void removeBonus(double bonus){
        if(this.Bonus<bonus){
            resetBonus();
        }
        else {
            this.Bonus-=bonus;
        }
    }
    public void resetBonus(){this.Bonus=0;}

    public double CalculateSalary(){return this.Wage*this.ShiftWorked+this.Bonus;}

    public void setContract(String contract){this.Contract = contract;}

    // function to set the number of shifts worker works
    public int getShiftWorked(){
        return this.ShiftWorked;
    }

    public double getWage(){return this.Wage;}

    public List<Days> getWeeklyWorkingDays() {
        return WeeklyWorkingDays;
    }

    public void Printme(){
        System.out.println("Name: "+ this.getName()+" with ID: "+this.getID());
    }
}
