package Ship.DataAccess;

import Ship.Bussiness.Driver;
import Ship.Bussiness.Truck;
import resource.Connect;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
public class TruckMapper {
    private static TruckMapper instance = new TruckMapper();
    private static Map<String, Truck> truckMap;
    private static Connection connect;
    private TruckMapper()
    {
        truckMap = new HashMap<>();
        connect = Connect.getConnection();
    }
    public static TruckMapper getInstance()
    {
        return instance;
    }
    public static Truck getTruck(String ID)
    {
        if(truckMap.get(ID) == null){
            readTruck(ID);
        }
        return truckMap.get(ID);
    }





}
