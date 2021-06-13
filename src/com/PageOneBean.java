package com;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.event.ActionEvent;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.simple.JSONArray;

@ManagedBean
@SessionScoped
public class PageOneBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Parent selectedParent;

	private String name;

	public PageOneBean() {
		fetchDataForFirstPage();
		filterCompleteParentDataForFirstPage();

		fetchDataForFirstPageChild();

	}

	private ArrayList<Parent> firstPageParentArrayList;

	private ArrayList<Parent> currentPageParentArrayList = new ArrayList<Parent>();

	public void page2ActionListener(ActionEvent event) {

		currentPageParentArrayList.clear();

		for (int i = 0; i < p.length; i++) {

			if (p[i].getId() == 3 || p[i].getId() == 4) {
				currentPageParentArrayList.add(p[i]);
			}
		}

		firstPageParentArrayList = currentPageParentArrayList;
	}

	public void page3ActionListener(ActionEvent event) {

		currentPageParentArrayList.clear();

		for (int i = 0; i < p.length; i++) {

			if (p[i].getId() == 5 || p[i].getId() == 6) {
				currentPageParentArrayList.add(p[i]);
			}
		}

		firstPageParentArrayList = currentPageParentArrayList;
	}

	public void page4ActionListener(ActionEvent event) {

		currentPageParentArrayList.clear();

		for (int i = 0; i < p.length; i++) {

			if (p[i].getId() == 7 || p[i].getId() == 8) {
				currentPageParentArrayList.add(p[i]);
			}
		}

		firstPageParentArrayList = currentPageParentArrayList;
	}

	public void page1ActionListener(ActionEvent event) {

		currentPageParentArrayList.clear();

		for (int i = 0; i < p.length; i++) {

			if (p[i].getId() == 1 || p[i].getId() == 2) {
				currentPageParentArrayList.add(p[i]);
			}
		}

		firstPageParentArrayList = currentPageParentArrayList;
	}

	public ArrayList<Parent> getFirstPageParentArrayList() {
		return firstPageParentArrayList;
	}

	public void setFirstPageParentArrayList(ArrayList<Parent> firstPageParentArrayList) {
		this.firstPageParentArrayList = firstPageParentArrayList;
	}

	public void filterCompleteParentDataForFirstPage() {

		firstPageParentArrayList = new ArrayList<Parent>();
		for (int i = 0; i < p.length; i++) {

			if (p[i].getId() == 1 || p[i].getId() == 2) {
				firstPageParentArrayList.add(p[i]);
			}
		}

	}

	public String getName() {
		name = "dddd";
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	JSONArray jsonReceievdArray;
	Parent[] p;
	Child[] c;

	// WEBSERVICE CONSUME CODE //
	// WE ARE FETCHING DATA FOR PARENT TABLE, RESULT OF WEBSERVICE WILL BE STORED IN
	// ARRAY OF OBJECTS FOR LATER USE
	public void fetchDataForFirstPage() {

		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/DAOFAB_REST/controller/parentmainpage");
		Response r = target.request(MediaType.APPLICATION_JSON).get();
		System.out.println("......" + target.request(MediaType.APPLICATION_JSON).get(Parent[].class));
		p = r.readEntity(Parent[].class);

	}

	// WE ARE FETCHING DATA FOR CHILD TABLE, RESULT OF WEBSERVICE WILL BE STORED IN
	// ARRAY OF OBJECTS FOR LATER USE
	public void fetchDataForFirstPageChild() {

		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/DAOFAB_REST/controller/childmainpage");
		Response r = target.request(MediaType.APPLICATION_JSON).get();
		System.out.println("......" + target.request(MediaType.APPLICATION_JSON).get(Child[].class));
		c = r.readEntity(Child[].class);

	}

	public ArrayList<Parent> readParentJsonToArrayList() {

		ArrayList<Parent> parentDataArrayList = new ArrayList<Parent>();
		JSONArray data = (JSONArray) jsonReceievdArray;

		ArrayList<Object> mm = new ArrayList<Object>();

		for (int i = 0; i < data.size(); i++) {
			mm.add(data.get(i));
		}

		return parentDataArrayList;
	}

	public ArrayList<Parent> getCurrentPageParentArrayList() {
		return currentPageParentArrayList;
	}

	public void setCurrentPageParentArrayList(ArrayList<Parent> currentPageParentArrayList) {
		this.currentPageParentArrayList = currentPageParentArrayList;
	}

	public Parent getSelectedParent() {

		return selectedParent;
	}

	public void setSelectedParent(Parent selectedParent) {
		this.selectedParent = selectedParent;
	}

	long selectedParentIdForDetail;

	private ArrayList<Child> arrayListChildSelectedForDetail;

	public ArrayList<Child> getArrayListChildSelectedForDetail() {
		return arrayListChildSelectedForDetail;
	}

	public void setArrayListChildSelectedForDetail(ArrayList<Child> arrayListChildSelectedForDetail) {
		this.arrayListChildSelectedForDetail = arrayListChildSelectedForDetail;
	}

	public String parentOnClickForDetail() {

		arrayListChildSelectedForDetail = new ArrayList<Child>();

		selectedParentIdForDetail = selectedParent.getId();

		for (int i = 0; i < c.length; i++) {

			if (c[i].getParentId() == selectedParentIdForDetail) {

				arrayListChildSelectedForDetail.add(c[i]);

			}

		}

		return "page2.xhtml?faces-redirect=true";

	}

	public String backToParent() {
		arrayListChildSelectedForDetail.clear(); // RESET
		filterCompleteParentDataForFirstPage(); // RESET

		return "page1.xhtml?faces-redirect=true";
	}

}
