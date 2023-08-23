package com.coles;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Convert {

    public static class TechRadarItem {
        @CsvBindByName(column = "name")
        private String name;

        @CsvBindByName(column = "ring")
        private String ring;

        @CsvBindByName(column = "quadrant")
        private String quadrant;

        @CsvBindByName(column = "isNew")
        private String isNew;

        @CsvBindByName(column = "description")
        private String description;

        @Override
        public String toString() {
            int ringToPrint = -1;
            switch (ring) {
                case "Adopt":
                    ringToPrint = 0;
                    break;
                case "Trial":
                    ringToPrint = 1;
                    break;
                case "Assess":
                    ringToPrint = 2;
                    break;
                case "Hold":
                    ringToPrint = 3;
                    break;
            }
            int quadrantToPrint = -1;
            switch (quadrant) {
                case "Techniques":
                    quadrantToPrint = 0;
                    break;
                case "Platforms":
                    quadrantToPrint = 1;
                    break;
                case "Tools":
                    quadrantToPrint = 2;
                    break;
                case "Languages and frameworks":
                    quadrantToPrint = 3;
                    break;
            }

            return "{" +
                    "\"label\":\"" + name + '\"' +
                    ", \"ring\":" + ringToPrint +
                    ", \"quadrant\":" + quadrantToPrint  +
                    ", \"active\":\"" + Boolean.valueOf(isNew) + '\"' +
                    ", \"description\":\"" + description + '\"' +
                    ", \"moved\":\"" + "false" + '\"' +
                    "},";
        }
    }

    public static void main(String[] args) {
        try (FileReader fileReader = new FileReader("Tech Radar - Sheet1.csv")) {
            CsvToBean<TechRadarItem> csvToBean = new CsvToBeanBuilder<TechRadarItem>(fileReader)
                    .withType(TechRadarItem.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<TechRadarItem> techRadarItems = csvToBean.parse();

            for (TechRadarItem item : techRadarItems) {
                System.out.println(item);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}