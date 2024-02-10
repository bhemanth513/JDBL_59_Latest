package com.gfg.minorproject.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchBookRequest {

    @NotBlank
    private String searchKey;
    @NotBlank
    private String searchVal;
    @NotBlank
    private String operator;

    private boolean available;

    private static Set<String> allowedKeys =  new HashSet<>();
    private static Map<String, List<String>> allowedOperatorsMap = new HashMap<>();

    public boolean validate() {
        allowedKeys.addAll(Arrays.asList("name","author_name","genre","pages","id"));
        allowedOperatorsMap.put("name",Arrays.asList("=","like"));
        allowedOperatorsMap.put("author_name",Arrays.asList("="));
        allowedOperatorsMap.put("genre",Arrays.asList("="));
        allowedOperatorsMap.put("pages",Arrays.asList("<","<=",">",">=","="));
        allowedOperatorsMap.put("id",Arrays.asList("="));
        if(!allowedKeys.contains(this.searchKey)){
            return false;
        }
        List<String> validOperators= allowedOperatorsMap.get(this.searchKey);
        if(!validOperators.contains(this.operator)){
            return false;
        }
        return true;
    }
}
