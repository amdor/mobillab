/**
 * Skyscraper API
 * API description in Markdown.
 *
 * OpenAPI spec version: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package herokuapp.autocomparator.zsolt.skyscraper.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.math.BigDecimal;
import io.swagger.annotations.*;
import com.google.gson.annotations.SerializedName;

@ApiModel(description = "")
public class CarDetail {
  
  @SerializedName("CarUri")
  private String carUri = null;

  @SerializedName("prod_date")
  @ColumnInfo(name = "prod_date")
  private String prodDate = null;

  @SerializedName("power")
  @ColumnInfo(name = "power")
  private String power = null;

  @SerializedName("price")
  @ColumnInfo(name = "price")
  private String price = null;

  @SerializedName("speedometer")
  @ColumnInfo(name = "speedometer")
  private String speedometer = null;

  @SerializedName("worth")
  @ColumnInfo(name = "worth")
  private Float worth = null;

  /**
   **/
  @ApiModelProperty(required = true, value = "")
  public String getCarUri() {
    return carUri;
  }
  public void setCarUri(String carUri) {
    this.carUri = carUri;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public String getProdDate() {
    return prodDate;
  }
  public void setProdDate(String prodDate) {
    this.prodDate = prodDate;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public String getPower() {
    return power;
  }
  public void setPower(String power) {
    this.power = power;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public String getPrice() {
    return price;
  }
  public void setPrice(String price) {
    this.price = price;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public String getSpeedometer() {
    return speedometer;
  }
  public void setSpeedometer(String speedometer) {
    this.speedometer = speedometer;
  }

  /**
   **/
  @ApiModelProperty(required = true, value = "")
  public Float getWorth() {
    return worth;
  }
  public void setWorth(Float worth) {
    this.worth = worth;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CarDetail carDetail = (CarDetail) o;
    return (this.carUri == null ? carDetail.carUri == null : this.carUri.equals(carDetail.carUri)) &&
        (this.prodDate == null ? carDetail.prodDate == null : this.prodDate.equals(carDetail.prodDate)) &&
        (this.power == null ? carDetail.power == null : this.power.equals(carDetail.power)) &&
        (this.price == null ? carDetail.price == null : this.price.equals(carDetail.price)) &&
        (this.speedometer == null ? carDetail.speedometer == null : this.speedometer.equals(carDetail.speedometer)) &&
        (this.worth == null ? carDetail.worth == null : this.worth.equals(carDetail.worth));
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + (this.carUri == null ? 0: this.carUri.hashCode());
    result = 31 * result + (this.prodDate == null ? 0: this.prodDate.hashCode());
    result = 31 * result + (this.power == null ? 0: this.power.hashCode());
    result = 31 * result + (this.price == null ? 0: this.price.hashCode());
    result = 31 * result + (this.speedometer == null ? 0: this.speedometer.hashCode());
    result = 31 * result + (this.worth == null ? 0: this.worth.hashCode());
    return result;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class CarDetail {\n");
    
    sb.append("  carUri: ").append(carUri).append("\n");
    sb.append("  prodDate: ").append(prodDate).append("\n");
    sb.append("  power: ").append(power).append("\n");
    sb.append("  price: ").append(price).append("\n");
    sb.append("  speedometer: ").append(speedometer).append("\n");
    sb.append("  worth: ").append(worth).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
