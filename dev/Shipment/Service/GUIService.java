package Shipment.Service;

import Shipment.Bussiness.ServiceController;
import Shipment.Bussiness.Shipment;
import Shipment.Bussiness.shipmentManagement;

import java.security.KeyPair;
import java.util.List;

public class GUIService {
    private static GUIService instance;
    private ServiceController serviceController;

    private GUIService() {
        serviceController = ServiceController.getInstance();
    }

    public static GUIService getInstance() {
        if (instance == null) {
            instance = new GUIService();
        }
        return instance;
    }

    public List<String[]> getTrucksData() {
        return serviceController.getTrucksData();
    }

    public List<String[]> getSiteData() {
        return serviceController.getSiteData();
    }

    public List<List<String>> getDriversData() {
        return serviceController.getDriversData();
    }

    /**
     * get the names of the sites of the executed shipment.
     *
     * @return List of string contains the names.
     */
    public String[] getSitesOfShipmentData() {
        return serviceController.getSitesNames();
    }

    public List<String> getItemsFromDoc(String siteName) {
        return serviceController.getItemsFromDoc(siteName);
    }

    public List<String[]> getOrdersData() {
        return serviceController.getOrdersData();
    }

    public List<String[]> getOrdersItemsData() {
        return serviceController.getOrdersItemsData();
    }

    public StringBuilder getShipmentString(String id) {
        return serviceController.getShipmentString(id);
    }

    public StringBuilder getAShipmentString(String id) {
        return serviceController.getAShipmentString(id);
    }

    public String[] getAShipmentsIDs()
    {
        return serviceController.getAShipmentsIDs();
    }
    public String[] getShipmentsIDs()
    {
        return serviceController.getShipmentsIDs();
    }
}
