package parking.refactored;

import java.util.List;

public class CsvReportFormatter extends ReportFormatter{
    @Override
    public String formatDailyReport(List<String> receipts, double total){
        StringBuilder sb=new StringBuilder("Plate,Type,Hours,Amount,Payment\n");

        for(String receipt:receipts){
            String[] data=receipt.split(" \\| ");
            sb.append(data[0]).append(",")
                    .append(data[1]).append(",")
                    .append(data[2].replace(" h","")).append(",")
                    .append(data[3].replace(" MAD","")).append(",")
                    .append(data[4]).append("\n");
        }

        sb.append("Total,").append(total).append(",");
        return sb.toString();
    }
}
