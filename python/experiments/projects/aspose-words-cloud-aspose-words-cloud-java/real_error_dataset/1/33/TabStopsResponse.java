/*
 * --------------------------------------------------------------------------------
 * <copyright company="Aspose" file="TabStopsResponse.java">
 *   Copyright (c) 2020 Aspose.Words for Cloud
 * </copyright>
 * <summary>
 *   Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 * 
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 * 
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 * </summary>
 * --------------------------------------------------------------------------------
 */

package com.aspose.words.cloud.model;

import java.util.Objects;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import org.threeten.bp.OffsetDateTime;
import com.aspose.words.cloud.model.*;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This response should be returned by the service when handling:
 * GET https://api.aspose.cloud/v4.0/words/Test.doc/paragraphs/{0}/tabstops.
 */
@ApiModel(description = "This response should be returned by the service when handling: GET https://api.aspose.cloud/v4.0/words/Test.doc/paragraphs/{0}/tabstops.")
public class TabStopsResponse extends WordsResponse {
    @SerializedName("TabStops")
    private List<TabStop> tabStops = null;
    public TabStopsResponse tabStops(List<TabStop> tabStops) {
        this.tabStops = tabStops;
        return this;
    }

    public TabStopsResponse addTabStopsItem(TabStop tabStopsItem) {
        if (this.tabStops == null) {
            this.tabStops = new ArrayList<TabStop>();
        }
        this.tabStops.add(tabStopsItem);
        return this;
    }

    /**
     * Gets or sets represents a tab stop.
    * @return tabStops
    **/
    @ApiModelProperty(value = "Gets or sets represents a tab stop.")
    public List<TabStop> getTabStops() {
        return tabStops;
    }

    public void setTabStops(List<TabStop> tabStops) {
        this.tabStops = tabStops;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
    TabStopsResponse tabStopsResponse = (TabStopsResponse)o;
    return
        Objects.equals(this.tabStops, tabStopsResponse.tabStops) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tabStops, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TabStopsResponse {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    tabStops: ").append(toIndentedString(tabStops)).append("\n");
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