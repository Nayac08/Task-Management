package models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Notification {
    private int id;
    private String message;
    private LocalDateTime timestamp;
    private boolean isRead;
    
    public void markAsRead() {
    	
    }
    
    public String getMessage() {
    	return message;
    }
    
    public LocalDateTime getTimestamp() {
    	return timestamp;
    }
    
    public boolean isNotificationRead() {
    	return isRead;
    }
}
