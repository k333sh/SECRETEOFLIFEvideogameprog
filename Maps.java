//create map class 
public class Maps {
    String mapName;//   Stringmapname
    int mapYear; // int mapyear
    String mapId; //  String map.txt name or id
    boolean decrypted; // boolean that detects whether or not map is decrypted
    String secret; // String map secret 

    //constructor that accepts name year and id of maps 
    public Maps(String mapName, int mapYear, String mapId) {
        this.mapName = mapName;
        this.mapYear = mapYear;
        this.mapId = mapId;
        this.decrypted =  false ; // setEncryption(encrypted);
        this.secret = "";
    }
    //To string to print out Maps
    public String toString() {
        //print the name and all other map attributes together on a line
        return String.format("%5s %d %s isDecrypted? %b %s", mapName, mapYear, mapId, decrypted, secret);
    }

    // method to return mapName 
    public String getmapName() {
        return this.mapName;
    }

    // method to return mapId
    public String getmapId() {
        return this.mapId;
    }

    // method to return mapYear 
    public int getmapYear() {
        return this.mapYear;
    }

    // method to return mapEncryption status 
    public boolean getmapEncryption() {
        return this.decrypted;
    }

    // method to return map Secret 
    public String getmapSecret() {
        return this.secret;
    }

    // method to set a decryption value
    public void setEncryption(boolean a) {
        this.decrypted = a;
    }
    // method to set a secret String 
    public void setSecret(String a ) {
        this.secret = a ;
    }

}
