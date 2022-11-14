package algonquin.cst2335.brow1396;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ChatMessage {

    @PrimaryKey (autoGenerate = true)
    @ColumnInfo(name="id")
    public int id;

    @ColumnInfo(name="message")
    public String message;

    @ColumnInfo(name="TimeSent")
    public String timeSent;

    @ColumnInfo(name="SendOrReceive")
    public boolean isSentButton;

public ChatMessage(){}

    public void setMessage(String message){
    this.message = message;
    }

    public void setTime(String time){
        this.timeSent = time;
    }

    public void setBoolean(boolean sentButton){
        this.isSentButton = sentButton;
    }

    public String getMessage(){
    return message;
    }

    public String getTimeSent(){
        return timeSent;
    }

    public boolean getIsSentButton(){
        return isSentButton;
    }

}
