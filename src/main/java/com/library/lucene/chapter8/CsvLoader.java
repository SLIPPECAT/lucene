package com.library.lucene.chapter8;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CsvLoader {
    public List<RestaurantInfoVo> readRestaurantnfo(){
        // CSV 파일 경로를 설정한다.
        String fileName = "/Users/ryujun-yeong/Documents/projects/common/lib/lucene/src/main/resources/data/restaurant_info.csv";
        List<RestaurantInfoVo> restaurantInfoList = null;

        try(Reader reader = Files.newBufferedReader(Paths.get(fileName))){
            CsvToBean csvToBean = new CsvToBeanBuilder(reader)
                    .withType(RestaurantInfoVo.class)
                    .withSkipLines(1)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            restaurantInfoList = csvToBean.parse();
        }catch (Exception e){
            e.printStackTrace();
        }
        return restaurantInfoList;
    }
}
