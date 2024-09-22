package az.babayev.springrest.util;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

@Service
public class DynamicFiltering {

    public MappingJacksonValue filterThis(Object data, String dtoName, String... fields) {

        SimpleBeanPropertyFilter beanPropertyFilter =
                SimpleBeanPropertyFilter.filterOutAllExcept(fields);

        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter(dtoName, beanPropertyFilter);

        MappingJacksonValue jacksonValue = new MappingJacksonValue(data);


        jacksonValue.setFilters(filterProvider);

        return jacksonValue;

    }
}
