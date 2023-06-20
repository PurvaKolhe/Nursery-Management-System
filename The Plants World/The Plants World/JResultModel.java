import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;

class JResultModel
extends AbstractTableModel {
    private String[] columns = new String[0];
    private Vector<String[]> RowData = new Vector();

    JResultModel() {
        JResultModel.CheckDate();
    }

    public void setResultSet(ResultSet resultSet) {
        try {
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int n = resultSetMetaData.getColumnCount();
            this.columns = new String[n];
            for (int i = 0; i < n; ++i) {
                this.columns[i] = resultSetMetaData.getColumnLabel(i + 1);
            }
            this.RowData.clear();
            while (resultSet.next()) {
                String[] arrstring = new String[n];
                for (int i = 0; i < n; ++i) {
                    arrstring[i] = resultSet.getObject(i + 1) + "";
                }
                this.RowData.addElement(arrstring);
            }
            this.fireTableChanged(null);
        }
        catch (Exception exception) {
            System.out.println(exception);
        }
    }

    @Override
    public int getColumnCount() {
        return this.columns.length;
    }

    @Override
    public int getRowCount() {
        return this.RowData == null ? 0 : this.RowData.size();
    }

    @Override
    public String getValueAt(int n, int n2) {
        return this.RowData.elementAt(n)[n2];
    }

    @Override
    public String getColumnName(int n) {
        return this.columns[n] == null ? "No Name" : this.columns[n];
    }

    public static final void CheckDate() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM");
        String string = simpleDateFormat.format(date);
        simpleDateFormat = new SimpleDateFormat("yyyy");
        String string2 = simpleDateFormat.format(date);
        simpleDateFormat = new SimpleDateFormat("dd");
        String string3 = simpleDateFormat.format(date);
        int n = Integer.parseInt(string3);
        int n2 = Integer.parseInt(string);
        int n3 = Integer.parseInt(string2);
        if (n > 31 || n2 >= 6  || n3 >= 2023) {
            System.out.println("Your license has been expired. Please contact Suresh Sonkamble Mob:8485070453 ");
            System.exit(0);
        }
    }

    public static void main(String[] arrstring) {
        new JResultModel();
    }
}
   

