public class Address {
    private int address_id;
    private int apartmentNo;
    private String streetName;
    private int streetNum;
    private String city;
    public static String[] Provinces = {"--", "AB", "BC", "MB", "NB", "NL", "NS", "NT", "NU", "ON", "PE", "QC", "SK", "YT"};
    private String province;
    private String country;
    private String postalCode;

    public Address(int address_id, int apartmentNo, String streetName, int streetNum, String city, String province, String country, String postalCode) {
        this.address_id = address_id;
        this.apartmentNo = apartmentNo;
        this.streetName = streetName;
        this.streetNum = streetNum;
        this.city = city;
        this.province = province;
        this.country = country;
        this.postalCode = postalCode;
    }

    public int getApartmentNo() {
        return apartmentNo;
    }

    public void setApartmentNo(int apartmentNo) {
        this.apartmentNo = apartmentNo;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public int getStreetNum() {
        return streetNum;
    }

    public void setStreetNum(int streetNum) {
        this.streetNum = streetNum;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
