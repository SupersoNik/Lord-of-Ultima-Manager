package com.avalutions.lou.manager.common;

import com.avalutions.lou.manager.models.City;
import com.avalutions.lou.manager.models.MeasurableUnit;
import com.avalutions.lou.manager.models.Resource;

public class TradeRequestItem {
    private City city;
    private MeasurableUnit<Resource> totalAvailable;
    private MeasurableUnit<Resource> cartAvailable;
    private MeasurableUnit<Resource> shipAvailable;
    private Time cartMoveTime;
    private Time shipMoveTime;
    
    public TradeRequestItem() {
        
    }
    
    public TradeRequestItem(City city, MeasurableUnit<Resource> totalAvailable, MeasurableUnit<Resource> cartAvailable, MeasurableUnit<Resource> shipAvailable, Time cartMoveTime, Time shipMoveTime) {
        this.city = city;
        this.totalAvailable = totalAvailable;
        this.cartAvailable = cartAvailable;
        this.shipAvailable = shipAvailable;
        this.cartMoveTime = cartMoveTime;
        this.shipMoveTime = shipMoveTime;
    }
    
    public City getCity() {
        return city;
    }
    public void setCity(City city) {
        this.city = city;
    }
    public MeasurableUnit<Resource> getTotalAvailable() {
        return totalAvailable;
    }
    public void setTotalAvailable(MeasurableUnit<Resource> totalAvailable) {
        this.totalAvailable = totalAvailable;
    }
    public MeasurableUnit<Resource> getCartAvailable() {
        return cartAvailable;
    }
    public void setCartAvailable(MeasurableUnit<Resource> cartAvailable) {
        this.cartAvailable = cartAvailable;
    }
    public MeasurableUnit<Resource> getShipAvailable() {
        return shipAvailable;
    }
    public void setShipAvailable(MeasurableUnit<Resource> shipAvailable) {
        this.shipAvailable = shipAvailable;
    }
    public Time getCartMoveTime() {
        return cartMoveTime;
    }
    public void setCartMoveTime(Time cartMoveTime) {
        this.cartMoveTime = cartMoveTime;
    }
    public Time getShipMoveTime() {
        return shipMoveTime;
    }
    public void setShipMoveTime(Time shipMoveTime) {
        this.shipMoveTime = shipMoveTime;
    }
}
