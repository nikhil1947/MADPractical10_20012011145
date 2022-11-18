package com.example.madpractical10_20012011145
import org.json.JSONObject
import java.io.Serializable

class Person (jsonObject: JSONObject) :Serializable {
    var id:String
    var Name: String
    var EmailId: String
    var PhoneNo: String
    var Address: String
    var Latitude: Double
    var Longitude:Double
    /**
     * {
    "id": "62fc930c5831f21801d78da8",
    "email": "guerra_rodgers@gnu.ac.in",
    "phone": "+919289635723",
    "profile": {
    "name": "Guerra Rodgers",
    "address": "88 College Place, Umapine, Oregon",
    "location": {
    "lat": 35.924816,
    "long": -85.003777
    }
    }
    }
     */
    /*
    * [
    * {"_id":"5f8d677c68d8ae7ceab6a732",
    * "name":{"first":"Lloyd","last":"York"},
    * "email":"lloyd.york@undefined.net",
    * "phone":"+1 (817) 545-3660",
    * "address":"311 Livonia Avenue, Belva, Ohio, 6019"}*/
    init {
        id = jsonObject.getString("id")
        EmailId = jsonObject.getString("email")
        PhoneNo = jsonObject.getString("phone")
        val profileJson = jsonObject.getJSONObject("profile")
        Name = profileJson.getString("name")
        Address = profileJson.getString("address")
        val locationJson = profileJson.getJSONObject("location")
        Latitude = locationJson.getDouble("lat")
        Longitude = locationJson.getDouble("long")
    }

    override fun toString(): String {
        return "$Name\n$PhoneNo\n$EmailId\n$Address\nlat:$Latitude\nlog:$Longitude"
    }
}