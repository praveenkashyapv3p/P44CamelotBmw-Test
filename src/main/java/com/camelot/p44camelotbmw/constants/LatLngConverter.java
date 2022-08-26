package com.camelot.p44camelotbmw.constants;

public class LatLngConverter {
    private static final String[] ORIENTATIONS = "N/S/E/W".split("/");
    
    /**
     * Given a decimal longitudinal coordinate such as <i>-79.982195</i> it will
     * be necessary to know whether it is a latitudinal or longitudinal
     * coordinate in order to fully convert it.
     *
     * @param coord coordinate in decimal format
     * @return coordinate in D°M′S″ format
     * @see <a href='https://goo.gl/pWVp60'>Geographic coordinate conversion
     * (wikipedia)</a>
     */
    private static String decimalToDMS(float coord) {
        
        float mod = coord % 1;
        int intPart = (int) coord;
        
        String degrees = String.valueOf(intPart);
        
        coord = mod * 60;
        mod = coord % 1;
        intPart = (int) coord;
        if (intPart < 0)
            intPart *= -1;
        
        String minutes = String.valueOf(intPart);
        
        coord = mod * 60;
        intPart = (int) coord;
        if (intPart < 0)
            intPart *= -1;
        
        String seconds = String.valueOf(intPart);
        
        return Math.abs(Integer.parseInt(degrees)) + "°" + minutes + "'" + seconds + "\"";
    }
    
    public String processCoordinates(float[] coordinates) {
        String converted0 = LatLngConverter.decimalToDMS(coordinates[1]);
        final String dmsLat = coordinates[0] > 0 ? ORIENTATIONS[0] : ORIENTATIONS[1];
        converted0 = converted0.concat(" ").concat(dmsLat);
        
        String converted1 = LatLngConverter.decimalToDMS(coordinates[0]);
        final String dmsLng = coordinates[1] > 0 ? ORIENTATIONS[2] : ORIENTATIONS[3];
        converted1 = converted1.concat(" ").concat(dmsLng);
        
        return converted0.concat(", ").concat(converted1);
    }
    
}