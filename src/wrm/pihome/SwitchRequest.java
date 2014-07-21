package wrm.pihome;

public class SwitchRequest
{
  private final int id;
  
  public SwitchRequest(int id){
    this.id = id;
  }
  
  public int getId(){
    return id;
  }
}