package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import org.primefaces.event.SelectEvent;

import Utils.DBConnector;
import models.Function;
import models.GroupFunction;
import models.Param;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;

@ManagedBean(name = "mainViewBean")
@ViewScoped
public class MainViewBean implements Serializable {

	private static final long serialVersionUID = 3278317840051591129L;

	private DBConnector dbConnector;

	private List<GroupFunction> groups = new ArrayList<GroupFunction>();
	private GroupFunction selectedGroup;

	private List<Function> functions;
	private Function selectedFunction;
	
	private List<Param> params;

	@PostConstruct
	private void init() {
		dbConnector = DBConnector.getInstance();
		dbConnector.initJDBC("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@v-ors.com.ua:1521:xe", "system",
				"oracle");

		ResultSet rs = dbConnector.selectQuery(
				"select G.ID, G.NAME, G.DESCR, (select count(ID) from CMD.FUNCTIONS where ID_GROUP=G.ID) COUNT from CMD.GROUP_FUNCTION G");

		try {
			while (rs.next()) {
				GroupFunction group = new GroupFunction(Long.parseLong(rs.getString("ID")), rs.getString("NAME"), rs.getString("DESCR"),
						rs.getInt("COUNT"));
				groups.add(group);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public List<GroupFunction> getGroups() {
		if (groups == null || groups.isEmpty()) {
			init();
		}
		return groups;
	}

	public GroupFunction getSelectedGroup() {
		return selectedGroup;
	}

	public void setSelectedGroup(GroupFunction selectedGroup) {
		this.selectedGroup = selectedGroup;
	}

	public List<Function> getFunctions() {
		if (selectedGroup != null) {
			if (functions == null) {
				functions = new ArrayList<Function>();
			} else {
				functions.clear();
			}
			ResultSet rs = dbConnector.selectQuery("select F.ID, F.NAME, F.DESCR, F.CTIME from CMD.FUNCTIONS F where F.ID_GROUP = " + selectedGroup.getId());
			try {
				while (rs.next()) {
					Function function = new Function(Long.parseLong(rs.getString("ID")), selectedGroup,
							rs.getString("NAME"), rs.getString("DESCR"), new Date(rs.getDate("CTIME").getTime()).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
					functions.add(function);
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} else {
			functions = null;
		}
		return functions;
	}

	public Function getSelectedFunction() {
		return selectedFunction;
	}

	public void setSelectedFunction(Function selectedFunction) {
		this.selectedFunction = selectedFunction;
	}
	
	public List<Param> getParams() {
		if (selectedFunction != null) {
			if (params == null) {
				params = new ArrayList<Param>();
			} else {
				params.clear();
			}
			ResultSet rs = dbConnector.selectQuery("select P.ID, P.NAME, P.DESCR, P.CTIME from CMD.FUN_PARAM P where P.ID_FUN = " + selectedFunction.getId());
			try {
				while (rs.next()) {
					Param param = new Param(Long.parseLong(rs.getString("ID")), selectedFunction,
							rs.getString("NAME"), rs.getString("DESCR"), new Date(rs.getDate("CTIME").getTime()).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
					params.add(param);
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} else {
			params = null;
		}
		return params;
	}

}
