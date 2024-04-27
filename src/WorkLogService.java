import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WorkLogService {
    public void addWorkLog(WorkLog workLog) {
        String sql = "INSERT INTO WorkLogs (EmployeeID, LogDate, WorkHours) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, workLog.getEmployeeID());
            stmt.setDate(2, workLog.getLogDate());
            stmt.setInt(3, workLog.getWorkHours());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
