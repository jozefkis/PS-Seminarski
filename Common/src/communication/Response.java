/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package communication;

/**
 *
 * @author Yo
 */
public class Response
{
    private Exception ex;
    private Object result;

    public Response(Exception ex, Object result)
    {
        this.ex = ex;
        this.result = result;
    }

    public Response()
    {
    }

    public Exception getEx()
    {
        return ex;
    }

    public void setEx(Exception ex)
    {
        this.ex = ex;
    }

    public Object getResult()
    {
        return result;
    }

    public void setResult(Object result)
    {
        this.result = result;
    }
}
