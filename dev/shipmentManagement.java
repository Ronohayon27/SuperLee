import java.util.*;

public class shipmentManagement {
    private final Map<String, List<Order>> vendorMap;
    private final List<Driver> drivers;
    private final List<Truck> trucks;
    private final List<Site> sites;

    private List<Shipment> shipments;

    public shipmentManagement(){
        vendorMap = new HashMap<>();
        drivers = new ArrayList<>();
        trucks = new ArrayList<>();
        sites = new ArrayList<>();
    }

    /****************************** Drivers related Methods ******************************/


    /**
     * This function checks if the ID of a driver is in the system.
     * @param ID ID of the driver.
     * @return true if found. false otherwise.
     */
    public boolean checkID(String ID){
        for (Driver driver : drivers) {
            if (Objects.equals(driver.getID(), ID)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This function creates a new driver and adds it to the system.
     * @param name drivers name
     * @param ID drivers ID
     * @param license license type. (C/D)
     * @param train training type. (regular/cooling/freezer)
     */
    public void addDriver(String name, String ID, char license, Training train){
        Driver driver = new Driver(name, ID, license, train);
        drivers.add(driver);
    }

    /**
     * This function deletes driver from the system.
     * @param ID drivers ID.
     */
    public void removeDriver(String ID){
        for (Driver driver : drivers){
            if (Objects.equals(driver.getID(), ID)) {
                drivers.remove(driver);
            }
        }
    }

    /****************************** Truck related Methods ******************************/


    /**
     * This function checks if a truck number is already in the system.
     * @param truckNumber string, the truck number.
     * @return true if the truck is found. false otherwise.
     */
    public boolean checkTruckNumber(String truckNumber){
        for (Truck truck : trucks) {
            if (Objects.equals(truckNumber, truck.getTruckNumber())) {
                return true;
            }
        }
        return false;
    }

    /**
     * this function creates a new truck and adds it to they system.
     * @param truckNumber the number of the truck.
     * @param totalWeight the total weight of the truck.
     * @param truckWeight the weight of an empty truck.
     * @param model the model of the truck.
     * @param train training type. (regular/cooling/freezer)
     */
    public void addTruck(String truckNumber, int totalWeight, int truckWeight, String model, int train){
        Truck truck = null;
        switch (train){
            case 1:
                truck = new RegularTruck(truckNumber,totalWeight,truckWeight,model);
                break;

            case 2:
                truck = new CoolingTruck(truckNumber, totalWeight, truckWeight, model);
                break;

            case 3:
                truck = new FreezerTruck(truckNumber, totalWeight, truckWeight, model);
                break;
        }
        trucks.add(truck);
    }

    /**
     * This function removes a truck from the system.
     * @param truckNumber string, the truck number.
     */
    public void removeTruck(String truckNumber){
        for (Truck truck : trucks){
            if (Objects.equals(truckNumber, truck.getTruckNumber())) {
                trucks.remove(truck);
            }
        }

    }


    /****************************** Sites related Methods ******************************/

    /**
     * This function checks if a site is already exist in the system.
     * @param name String, name of the site.
     * @return true if found. false otherwise.
     */

    public boolean checkSite(String name){
        for(Site site : sites){
            if (Objects.equals(site.getName(), name))
                return true;
        }
        return false;
    }


    /**
     * This function creates a new vendor and adds it to the system.
     * @param name string, name of the vendor.
     * @param address string, address of the vendor.
     * @param phoneNumber string, phone number of the contact person.
     * @param contactName string, name of the contact person.
     */

    public void addVendor(String name, String address, String phoneNumber, String contactName){
        Vendor vendor;
        vendor = new Vendor(name, address, phoneNumber,contactName);
        List<Order> orderList = new ArrayList<>();
        vendorMap.put(name, orderList);
        sites.add(vendor);
        }

    /**
     * This function creates a new branch and adds it to the system.
     * @param name string, name of the branch.
     * @param address string, address of the vendor.
     * @param phoneNumber string, phone number of the contact person.
     * @param contactName string, name of the contact person.
     * @param zone enum Zone, zone area.
     */
    public void addBranch(String name, String address, String phoneNumber, String contactName, Zone zone){
        Branch branch;
        branch = new Branch(name, address, phoneNumber, contactName, zone);
        sites.add(branch);
    }

    public void delete
    }