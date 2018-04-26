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

import java.util.*;
import java.util.Map;
import io.swagger.annotations.*;
import com.google.gson.annotations.SerializedName;

@ApiModel(description = "")
public class CarQueryObject {
  
  @SerializedName("carUrls")
  private List<String> carUrls = null;

  /**
   **/
  @ApiModelProperty(value = "")
  public List<String> getCarUrls() {
    return carUrls;
  }
  public void setCarUrls(List<String> carUrls) {
    this.carUrls = carUrls;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CarQueryObject carQueryObject = (CarQueryObject) o;
    return this.carUrls == null ? carQueryObject.carUrls == null : this.carUrls.equals(carQueryObject.carUrls);
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + (this.carUrls == null ? 0: this.carUrls.hashCode());
    return result;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class CarQueryObject {\n");
    
    sb.append("  carUrls: ").append(carUrls).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
