package HR.Bussiness;

public class CantWork {
    private String reason;
    private double start;
    private double end;

    public CantWork(double s,double e,String r){
        reason=r;
        this.start=s;
        this.end=e;
    }
    public double getStart(){
        return start;
    }
    public double getEnd(){
        return end;
    }

    public String getReason() {
        return reason;
    }

    public void printMe(){
        String print="start: "+start+ " end: "+end;
        if(!reason.equals("")){
            print+=" reason: "+reason;
        }
        System.out.println(print);
    }
    public String toString(){
        String print="start: "+start+ " end: "+end;
        if(!reason.equals("")){
            print+=" reason: "+reason;
        }
        return print;
    }
}
