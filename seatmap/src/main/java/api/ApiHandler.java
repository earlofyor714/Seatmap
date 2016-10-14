package api;

import api.objects.RowDto;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/RowService")
public class ApiHandler {
    private RowDto rowDto;

    @Path("/rows")
    @Produces(MediaType.APPLICATION_JSON)
    public RowDto getRows() {
        return rowDto;
    }

    @Path("/set")
    @Consumes(MediaType.APPLICATION_JSON)
    public void setRows(RowDto rowDto) {
        this.rowDto = rowDto;
        System.out.println("data saved");
    }
}
