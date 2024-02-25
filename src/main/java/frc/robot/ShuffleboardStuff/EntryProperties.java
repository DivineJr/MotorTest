package frc.robot.ShuffleboardStuff;

import java.util.ArrayList;

import com.fasterxml.jackson.databind.deser.impl.ObjectIdReferenceProperty.PropertyReferring;

import edu.wpi.first.wpilibj.shuffleboard.WidgetType;

public class EntryProperties {
    /* TODO:
     * This class is meant to be an object that handles similarly to the creation of a widget
     * as standard in Shuffleboard, and store it in order for use in ShuffleboardHandler and
     * ShuffleList
     * 
     * withWidget
     * withProperties
     * withPosition
     * withSize
     * 
     */
    
    private int positionX;
    private int positionY;
    private int sizeX;
    private int sizeY;
    private WidgetType widgetType;
    private ArrayList<mapDataHolder<?>> propertyList = new ArrayList<mapDataHolder<?>>();
    /*
     * Possible results for key-result pair
     * 
     * Double
     * Integer
     * Boolean
     * String
     */

    public EntryProperties withWidget(WidgetType widgetType) {
        this.widgetType = widgetType;

        return this;
    }

    public EntryProperties withProperties(String keyValue, ResultTypes resultType, String resultValue) {
        switch(resultType) {
            case DOUBLE:
                propertyList.add(new mapDataHolder<Double>(keyValue, Double.parseDouble(resultValue), resultType));
                break;
            case INTEGER:
                propertyList.add(new mapDataHolder<Integer>(keyValue, Integer.parseInt(resultValue), resultType));
                break;
            case BOOLEAN:
                propertyList.add(new mapDataHolder<Boolean>(keyValue, Boolean.parseBoolean(resultValue), resultType));
                break;
            case STRING:
                propertyList.add(new mapDataHolder<String>(keyValue, resultValue, resultType));
                break;
            default: 
                return this; //return without doing any work
        }
        return this;
    }

    public EntryProperties withPosition(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;

        return this;
    }

    public EntryProperties withSize(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;

        return this;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public WidgetType getWidgetType() {
        return widgetType;
    }

    public ArrayList<mapDataHolder<?>> getPropertyList() {
        return propertyList;
    }

    public static enum ResultTypes {
        DOUBLE("class java.lang.Double"),
        INTEGER("class java.lang.Integer"),
        BOOLEAN("class java.lang.Boolean"),
        STRING("class java.lang.String");

        public final String className;

        ResultTypes(String className) {
            this.className = className;
        }
    }
    
    public EntryProperties() {

    }

    /**
     * Holds the data that is meant to go into the SimpleWidget withProperty
     * @param retType
     */
    public class mapDataHolder<retType> {
        private String keyValue;
        private retType returnValue;
        private ResultTypes type;

        /**
         * 
         * @param keyValue      The key for the map
         * @param returnValue   The return value of the related key
         * @param returnType    The variable type of the return value
         */
        public mapDataHolder(String keyValue, retType returnValue, ResultTypes returnType) {
            this.keyValue = keyValue;
            this.returnValue = returnValue;
            this.type = returnType;
        }

        public String getKey() {
            return keyValue;
        }

        public retType getReturn() {
            return returnValue;
        }

        public ResultTypes getType() {
            return type;
        }
    }
}
