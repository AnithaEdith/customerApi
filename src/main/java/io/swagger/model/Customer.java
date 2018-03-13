package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.persistence.*;

/**
 * Customer
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-03-12T07:12:35.584Z")
@Entity
public class Customer   {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @JsonProperty("id")
  private Integer id ;

  @Column
  @JsonProperty("name")
  private String name = null;

  @Column
  @JsonProperty("contactnumber")
  private int contactnumber ;

  @Column
  @JsonProperty("email")
  private String email = null;

  @Column
  @JsonProperty("address")
  private String address = null;

  @Column
  @JsonProperty("state")
  private String state = null;

  @Column
  @JsonProperty("city")
  private String city = null;

  @Column
  @JsonProperty("zipcode")
  private int zipcode ;

  public Customer() {
    super();
  }

  public Customer(int id, String name, int contactnumber, String email, String address, String state, String city, int zipcode) {
    this.id = id;
    this.name = name;
    this.contactnumber = contactnumber;
    this.email = email;
    this.address = address;
    this.state = state;
    this.city = city;
    this.zipcode = zipcode;
  }

  public Customer id(int id) {
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(example = "d290f1ee-6c54-4b01-90e6-d701748f0851", required = true, value = "")
  @NotNull
  @Valid
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Customer name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(example = "Customer 1", required = true, value = "")
  @NotNull


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Customer contactnumber(int contactnumber) {
    this.contactnumber = contactnumber;
    return this;
  }

   /**
   * Get contactnumber
   * @return contactnumber
  **/
  @ApiModelProperty(example = "123.0", required = true, value = "")
  @NotNull
  @Valid
  public int getContactnumber() {
    return contactnumber;
  }

  public void setContactnumber(int contactnumber) {
    this.contactnumber = contactnumber;
  }

  public Customer email(String email) {
    this.email = email;
    return this;
  }

   /**
   * Get email
   * @return email
  **/
  @ApiModelProperty(example = "abc@def.com", required = true, value = "")
  @NotNull


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Customer address(String address) {
    this.address = address;
    return this;
  }

   /**
   * Get address
   * @return address
  **/
  @ApiModelProperty(example = "Address 1", required = true, value = "")
  @NotNull


  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Customer state(String state) {
    this.state = state;
    return this;
  }

   /**
   * Get state
   * @return state
  **/
  @ApiModelProperty(example = "Tamil Nadu", required = true, value = "")
  @NotNull


  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public Customer city(String city) {
    this.city = city;
    return this;
  }

   /**
   * Get city
   * @return city
  **/
  @ApiModelProperty(example = "Cuennai", required = true, value = "")
  @NotNull


  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public Customer zipcode(int zipcode) {
    this.zipcode = zipcode;
    return this;
  }

   /**
   * Get zipcode
   * @return zipcode
  **/
  @ApiModelProperty(example = "123451.0", required = true, value = "")
  @NotNull

  @Valid

  public int getZipcode() {
    return zipcode;
  }

  public void setZipcode(int zipcode) {
    this.zipcode = zipcode;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Customer customer = (Customer) o;
    return Objects.equals(this.id, customer.id) &&
        Objects.equals(this.name, customer.name) &&
        Objects.equals(this.contactnumber, customer.contactnumber) &&
        Objects.equals(this.email, customer.email) &&
        Objects.equals(this.address, customer.address) &&
        Objects.equals(this.state, customer.state) &&
        Objects.equals(this.city, customer.city) &&
        Objects.equals(this.zipcode, customer.zipcode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, contactnumber, email, address, state, city, zipcode);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Customer {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    contactnumber: ").append(toIndentedString(contactnumber)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    state: ").append(toIndentedString(state)).append("\n");
    sb.append("    city: ").append(toIndentedString(city)).append("\n");
    sb.append("    zipcode: ").append(toIndentedString(zipcode)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

