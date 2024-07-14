//Name     : Avishka Wishwanath
//Index No : PR23107180
// Update section and List section does not work properly. 
import javax.swing.table.*;
import java.time.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;

class ContactList{
	private Node start;
	
    //------------------------ADD METHOD------------------------------
    public void add(Contact contact){
        Node n1 =new Node(contact);
        Node temp=start;
        if(isEmpty()){
			start = n1;
		} else {
			while (temp.next!=null){
				temp = temp.next;
			}
			temp.next=n1;
		}
	}
	
	private boolean isEmpty(){
		return start==null;
	}
    
    private boolean isValidIndex(int index){
		return index>=0 && index<getSize();
	}
	public boolean getValid(int index){
		return isValidIndex(index);
	}
	
	public Contact get(int index){
		if(isValidIndex(index)){
			Node temp = start;
			int count = 0;
			while(count<index){
				count++;
				temp=temp.next;
			}
			return temp.contact;
		}
		return null;
	}
    
    public int getSize(){
		Node temp = start;
		int count=0;
		while(temp!=null){
			count++;
			temp = temp.next;
		}
		return count;
	}
	
    //------------------UPDATE------------------------
    
    public int searchUser(String userSearch){
		Scanner input = new Scanner(System.in);
		boolean isValid = false;
		int id = -1;
		Node temp = start;
		do{
			int index = 0;
			//---- Check user input in the arrays and return element ID of the array
			if(userSearch.charAt(0) == '0' && userSearch.length() == 10){
				while (temp != null){
					if (temp.contact.getPhone().equals(userSearch)){
						id = index;
						break;
					}
					temp=temp.next;
					index++;
				}
			}else {
				while (temp != null){
					if (temp.contact.getName().equals(userSearch)){
						id = index;
						break;
					}
					temp=temp.next;
					index++;
				}
			}
			return id;
		} while (!isValid);
	}
    
	//------ Delete User --------------
	private void remove(int index){
		if(index>=0 && index<getSize()){
			if (index == 0){
				start = start.next;
			}else{
				int count = 0;
				Node temp = start;
				while(count<index-1){
					temp = temp.next;
					count++;
				}
				temp.next = temp.next.next;
			}
		}
	}
	
	public void deleteUser(int id){
		remove(id);
	}
	//--------------//--------------//------------//---------------//
    
    //-------- Sort contact-------------
    
    public void sortByName(){
		Node temp = start, index=null;
		int i=1, j;
		if(start == null){
			return;
		} else {
			while(temp != null){
				index = temp.next;
				while (index!=null){
					if ( temp.contact.getName().compareTo(index.contact.getName())>0 ){
						Contact tempContact = temp.contact;
						temp.contact = index.contact;
						index.contact = tempContact;
					}
					index = index.next;
				}
				temp = temp.next;
			}
		}
		return;
	}
	
	public void sortBySalary(){
		Node temp = start, index=null;
		int i=1, j;
		if(start == null){
			return;
		} else {
			while(temp != null){
				index = temp.next;
				while (index!=null){
					if ( temp.contact.getSalary() > index.contact.getSalary() ){
						Contact tempContact = temp.contact;
						temp.contact = index.contact;
						index.contact = tempContact;
					}
					index = index.next;
				}
				temp = temp.next;
			}
		}
		return;
	}
	
	public void sortByBod(){
		Node temp = start, index=null;
		int i=1, j;
		if(start == null){
			return;
		} else {
			while(temp != null){
				index = temp.next;
				while (index!=null){
					if ( temp.contact.getBod().compareTo(index.contact.getBod())>0 ){
						Contact tempContact = temp.contact;
						temp.contact = index.contact;
						index.contact = tempContact;
					}
					index = index.next;
				}
				temp = temp.next;
			}
		}
		return;
	}
}

class Node {
		Contact contact;
		Node next;
		Node(Contact contact){
			this.contact = contact;
		}
	}

class Contact{
	private String id;
	private String name;
	private String phone;
	private String company;
	private double salary;
	private String bod;
	Contact (String id, String name, String phone, String company, double salary, String bod){
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.company = company;
		this.salary = salary;
		this.bod = bod;
	}
	
	public void setId(String id){
		this.id = id;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setPhone(String phone){
		this.phone = phone;
	}
	public void setCompany(String company){
		this.company = company;
	}
	public void setSalary(double salary){
		this.salary = salary;
	}
	public void setBod(String bod){
		this.bod = bod;
	}
	
	public String getId(){
		return id;
	}
	public String getName(){
		return name;
	}
	public String getPhone(){
		return phone;
	}
	public String getCompany(){
		return company;
	}
	public double getSalary(){
		return salary;
	}
	public String getBod(){
		return  bod;
	}
}
class ContactMainForm extends JFrame{
	//private Example example = new Example();
		private AddContacts addContacts;
		private UpdateContact updateContact;
		private DeleteContact deleteContact;
		private SearchContact searchContact;
		private ListContact listContact;
		
		private JButton btnAdd;
		private JButton btnUpdate;
		private JButton btnDelete;
		private JButton btnSearch;
		private JButton btnList;
		private JButton btnExit;
		
		ContactMainForm(){
			setSize(500,300);
			setTitle("Contact Manager");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setLocationRelativeTo(null);
			
			JPanel btnPanel = new JPanel(new GridLayout(6,1,3,3));
			
			btnAdd =new JButton("Add Contact");
			btnAdd.setFont(new Font("",1,25));
			btnAdd.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt){
					if(addContacts == null){
						addContacts = new AddContacts();
					}
					addContacts.setVisible(true);
				}
			});
			btnPanel.add(btnAdd);
			
			btnUpdate =new JButton("Update Contact");
			btnUpdate.setFont(new Font("",1,25));
			btnUpdate.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt){
					if(updateContact == null){
						updateContact = new UpdateContact();
					}
					updateContact.setVisible(true);
				}
			});
			btnPanel.add(btnUpdate);
			
			btnDelete =new JButton("Delete Contact");
			btnDelete.setFont(new Font("",1,25));
			btnDelete.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt){
					if(updateContact == null){
						deleteContact = new DeleteContact();
					}
					deleteContact.setVisible(true);
				}
			});
			btnPanel.add(btnDelete);
			
			btnSearch =new JButton("Search Contact");
			btnSearch.setFont(new Font("",1,25));
			btnSearch.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt){
					if(updateContact == null){
						searchContact = new SearchContact();
					}
					searchContact.setVisible(true);
				}
			});
			btnPanel.add(btnSearch);
			
			btnList =new JButton("List Contact");
			btnList.setFont(new Font("",1,25));
			btnList.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt){
					if(updateContact == null){
						listContact = new ListContact();
					}
					listContact.setVisible(true);
				}
			});
			btnPanel.add(btnList);
			
			btnExit =new JButton("Exit");
			btnExit.setFont(new Font("",1,25));
			btnExit.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt){
					System.exit(0);
				}
			});
			btnPanel.add(btnExit);
			
			add(btnPanel);
		}
}

class ListContact extends JFrame{
	private SortName sortName;
	private SortSalary sortSalary;
	private SortBod sortBod;
	
	private JLabel lblTitle;
	
	private JButton listName;
	private JButton listSalary;
	private JButton listBod;
	private JButton btnCancel;
	
	ListContact(){
		setSize(300, 300);
        setTitle("List Contacts");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        lblTitle = new JLabel("List Contact");
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
		lblTitle.setFont(new Font("",1,30));
		add("North",lblTitle);
		
		JPanel btnListPanel = new JPanel(new GridLayout(3,1,3,3));
		listName =new JButton("Sort by Name");
		listName.setFont(new Font("",1,25));
		listName.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent evt){
			if(sortName == null){
				sortName = new SortName();
			}
			sortName.setVisible(true);
		}
		});
		btnListPanel.add(btnPanel1(listName));
		
		listSalary =new JButton("Sort by Salary");
		listSalary.setFont(new Font("",1,25));
		listSalary.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent evt){
			if(sortName == null){
				sortSalary = new SortSalary();
			}
			sortSalary.setVisible(true);
		}
		});
		btnListPanel.add(btnPanel1(listSalary));
		
		listBod =new JButton("Sort by Birthday");
		listBod.setFont(new Font("",1,25));
		listBod.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent evt){
			if(sortBod == null){
				sortBod = new SortBod();
			}
			sortBod.setVisible(true);
		}
		});
		btnListPanel.add(btnPanel1(listBod));
		add(btnListPanel);
		
		JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        btnCancel = new JButton("Cancel");
        btnCancel.setFont(new Font("", 1, 15));
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
				dispose();
			}
		});
		btnPanel.add(btnCancel);

		add("South",btnPanel);
		
	}
	public JPanel btnPanel1(JButton btn){
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel.add(btn);
		return panel;
	}
}

class SortName extends JFrame{
	private JLabel  lblTitle;
	
	private JTable contactTable;
	private DefaultTableModel dtm;

	
	private JButton btnCancel;
	private JButton btnReload;
    
    public SortName(){
		setSize(900, 500);
        setTitle("Sort Contacts");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
		
		JPanel panel1 = new JPanel();
		
		
		
		lblTitle = new JLabel("List Contact by Name");
        lblTitle.setFont(new Font("", 1, 30));
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        add(lblTitle, BorderLayout.NORTH);
        
		JPanel mainPanel = new JPanel(new FlowLayout());
		JPanel searchPanel = new JPanel(new GridLayout(1, 4, 3, 3));
		JPanel topicPanel = new JPanel(new FlowLayout());
        JPanel lblPanel = new JPanel(new GridLayout(1, 2, 3, 3));
		JPanel lblExistingPanel = new JPanel(new GridLayout(6, 2, 3, 3));
		JPanel lblUpdatePanel = new JPanel(new GridLayout(6, 2, 3, 3));
		
		String[] columnName = {"Id",  "Name", "Phone No", "Company", "Salary", "Birthday"};
		dtm = new DefaultTableModel(columnName,0);
		contactTable = new JTable(dtm);
		JScrollPane tablePane=new JScrollPane(contactTable);

        add(tablePane, BorderLayout.CENTER);

       // lblPanel.add(txtPanel);
        
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        btnReload=new JButton("Reload");
		btnReload.setFont(new Font("",1,15));
		btnReload.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				Example.contactList.sortByName();
				dtm.setRowCount(0);
				for(int i=0; i<Example.contactList.getSize(); i++){
					ContactList contactList = Example.contactList;
					Object[] rowData={contactList.get(i).getId(), contactList.get(i).getName(),contactList.get(i).getPhone(),contactList.get(i).getCompany(), contactList.get(i).getSalary(), contactList.get(i).getBod()};
					dtm.addRow(rowData);
				}
			}
		});
		btnPanel.add(btnReload);
        
        btnCancel = new JButton("Cancel");
        btnCancel.setFont(new Font("", 1, 15));
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
				dispose();
			}
		});
		btnPanel.add(btnCancel);

		add("South",btnPanel);
	}
	
}

class SortBod extends JFrame{
	private JLabel  lblTitle;
	
	private JTable contactTable;
	private DefaultTableModel dtm;

	
	private JButton btnCancel;
	private JButton btnReload;
    
    public SortBod(){
		setSize(900, 500);
        setTitle("Sort Contacts");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
		
		JPanel panel1 = new JPanel();
		
		
		
		lblTitle = new JLabel("List Contact by Birthday");
        lblTitle.setFont(new Font("", 1, 30));
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        add(lblTitle, BorderLayout.NORTH);
        
		JPanel mainPanel = new JPanel(new FlowLayout());
		JPanel searchPanel = new JPanel(new GridLayout(1, 4, 3, 3));
		JPanel topicPanel = new JPanel(new FlowLayout());
        JPanel lblPanel = new JPanel(new GridLayout(1, 2, 3, 3));
		JPanel lblExistingPanel = new JPanel(new GridLayout(6, 2, 3, 3));
		JPanel lblUpdatePanel = new JPanel(new GridLayout(6, 2, 3, 3));
		
		String[] columnName = {"Id",  "Name", "Phone No", "Company", "Salary", "Birthday"};
		dtm = new DefaultTableModel(columnName,0);
		contactTable = new JTable(dtm);
		JScrollPane tablePane=new JScrollPane(contactTable);

        add(tablePane, BorderLayout.CENTER);

       // lblPanel.add(txtPanel);
        
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        btnReload=new JButton("Reload");
		btnReload.setFont(new Font("",1,15));
		btnReload.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				Example.contactList.sortByBod();
				dtm.setRowCount(0);
				for(int i=0; i<Example.contactList.getSize(); i++){
					ContactList contactList = Example.contactList;
					Object[] rowData={contactList.get(i).getId(), contactList.get(i).getName(),contactList.get(i).getPhone(),contactList.get(i).getCompany(), contactList.get(i).getSalary(), contactList.get(i).getBod()};
					dtm.addRow(rowData);
				}
			}
		});
		btnPanel.add(btnReload);
        
        btnCancel = new JButton("Cancel");
        btnCancel.setFont(new Font("", 1, 15));
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
				dispose();
			}
		});
		btnPanel.add(btnCancel);

		add("South",btnPanel);
	}
	
}

class SortSalary extends JFrame{
	private ContactList contactList;
	private JLabel  lblTitle;
	
	private JTable contactTable;
	private DefaultTableModel dtm;

	
	private JButton btnCancel;
	private JButton btnReload;
    
    public SortSalary(){
		setSize(900, 500);
        setTitle("List Contacts");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
		
		JPanel panel1 = new JPanel();
		
		
		
		lblTitle = new JLabel("List Contact by Salary");
        lblTitle.setFont(new Font("", 1, 30));
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        add(lblTitle, BorderLayout.NORTH);
        
		JPanel mainPanel = new JPanel(new FlowLayout());
		JPanel searchPanel = new JPanel(new GridLayout(1, 4, 3, 3));
		JPanel topicPanel = new JPanel(new FlowLayout());
        JPanel lblPanel = new JPanel(new GridLayout(1, 2, 3, 3));
		JPanel lblExistingPanel = new JPanel(new GridLayout(6, 2, 3, 3));
		JPanel lblUpdatePanel = new JPanel(new GridLayout(6, 2, 3, 3));
		
		String[] columnName = {"Id",  "Name", "Phone No", "Company", "Salary", "Birthday"};
		dtm = new DefaultTableModel(columnName,0);
		contactTable = new JTable(dtm);
		JScrollPane tablePane=new JScrollPane(contactTable);

        add(tablePane, BorderLayout.CENTER);

       // lblPanel.add(txtPanel);
        
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        btnReload=new JButton("Reload");
		btnReload.setFont(new Font("",1,15));
		btnReload.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				Example.contactList.sortBySalary();
				dtm.setRowCount(0);
				for(int i=0; i<Example.contactList.getSize(); i++){
					ContactList contactList = Example.contactList;
					Object[] rowData={contactList.get(i).getId(), contactList.get(i).getName(),contactList.get(i).getPhone(),contactList.get(i).getCompany(), contactList.get(i).getSalary(), contactList.get(i).getBod()};
					dtm.addRow(rowData);
				}
			}
		});
		btnPanel.add(btnReload);
        
        btnCancel = new JButton("Cancel");
        btnCancel.setFont(new Font("", 1, 15));
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
				dispose();
			}
		});
		btnPanel.add(btnCancel);

		add("South",btnPanel);
	}
	
}
class SearchContact extends JFrame{
	private JLabel lblTitle;
    private JLabel lblTopicSearch;
    private JLabel lblTopicUpdate;
    private JLabel lblId;
    private JLabel lblName;
    private JLabel lblPhone;
    private JLabel lblCompany;
    private JLabel lblSalary;
    private JLabel lblBod;
    private JLabel lblInvalid;
    private JLabel lblSearch;
    private JLabel lblError;
    private JLabel lblExistingId;
    

    private JButton btnDelete;
    private JButton btnCancel;
    private JButton btnSearchId;
    
    private JTextField txtSearchId;
    private JTextField txtName;
    private JTextField txtPhone;
    private JTextField txtCompany;
    private JTextField txtSalary;
    private JTextField txtBod;
    
    int intId;
    public SearchContact(){
		setSize(900, 500);
        setTitle("Delete Contacts");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
		
		lblTitle = new JLabel("DELETE CONTACT");
        lblTitle.setFont(new Font("", 1, 30));
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        add(lblTitle, BorderLayout.NORTH);
        
		JPanel mainPanel = new JPanel(new FlowLayout());
		JPanel searchPanel = new JPanel(new GridLayout(1, 4, 3, 3));
		JPanel lblPanel = new JPanel(new GridLayout(1, 2, 3, 3));
		
        JPanel searchnamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lblSearch = new JLabel("Name or Phone No: ");
        lblSearch.setFont(new Font("", 1, 20));
        searchnamePanel.add(lblSearch);
		searchPanel.add(searchnamePanel);

        txtSearchId = new JTextField(10);
        txtSearchId.setFont(new Font("", 1, 20));
        JPanel txtIdPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtIdPanel.add(txtSearchId);
        searchPanel.add(txtIdPanel);
		
		JPanel searchbtnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnSearchId = new JButton("Search");
        btnSearchId.setFont(new Font("", 1, 20));
        btnSearchId.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                intId = Example.contactList.searchUser(txtSearchId.getText());
                if (intId == -1) {
                    lblInvalid.setVisible(true);
                } else {
                    lblInvalid.setVisible(false);
					updateExistingContactLabels(intId);
                }
            }
        });
        
        
        searchbtnPanel.add(btnSearchId);
		searchPanel.add(searchbtnPanel);
		
        lblInvalid = new JLabel("<html><font color='red'>Not Found</font></html>");
        lblInvalid.setFont(new Font("", 1, 20));
        lblInvalid.setVisible(false);
        searchPanel.add(lblInvalid);

        mainPanel.add(searchPanel);
        
        add(mainPanel, BorderLayout.CENTER);

        JPanel txtPanel = new JPanel(new GridLayout(7, 2, 3, 3));
		
		lblId = new JLabel("ID");
        lblId.setFont(new Font("", 1, 20));
        txtPanel.add(lblId);
		
		lblExistingId = new JLabel();
        lblExistingId.setFont(new Font("", 1, 20));
        txtPanel.add(lblExistingId);
		
		lblName = new JLabel("Name");
        lblName.setFont(new Font("", 1, 20));
        txtPanel.add(lblName);
		
        txtName = new JTextField(10);
        txtName.setFont(new Font("", 1, 20));
        txtPanel.add(txtPanel(txtName));
		
		lblPhone = new JLabel("Phone");
        lblPhone.setFont(new Font("", 1, 20));
        txtPanel.add(lblPhone);
        
        txtPhone = new JTextField(10);
        txtPhone.setFont(new Font("", 1, 20));
        txtPanel.add(txtPanel(txtPhone));

		lblCompany = new JLabel("Company");
        lblCompany.setFont(new Font("", 1, 20));
        txtPanel.add(lblCompany);
		
        txtCompany = new JTextField(10);
        txtCompany.setFont(new Font("", 1, 20));
        txtPanel.add(txtPanel(txtCompany));
		
		lblSalary = new JLabel("Salary");
        lblSalary.setFont(new Font("", 1, 20));
        txtPanel.add(lblSalary);
        
        txtSalary = new JTextField(10);
        txtSalary.setFont(new Font("", 1, 20));
        txtPanel.add(txtPanel(txtSalary));
		
		lblBod = new JLabel("Birthday");
        lblBod.setFont(new Font("", 1, 20));
        txtPanel.add(lblBod);
        
        txtBod = new JTextField(10);
        txtBod.setFont(new Font("", 1, 20));
        txtPanel.add(txtPanel(txtBod));

       // lblPanel.add(txtPanel);
        
		mainPanel.add(txtPanel);
        add(mainPanel, BorderLayout.CENTER);
        
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        btnCancel = new JButton("Cancel");
        btnCancel.setFont(new Font("", 1, 15));
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
				dispose();
			}
		});
		btnPanel.add(btnCancel);

		add("South",btnPanel);
	}
	public void updateExistingContactLabels(int contactId) {
		Contact contact = Example.contactList.get(contactId);
		lblExistingId.setText(contact.getId());
		txtName.setText(contact.getName());
		txtPhone.setText(contact.getPhone());
		txtCompany.setText(contact.getCompany());
		txtSalary.setText(String.valueOf(contact.getSalary()));
		txtBod.setText(contact.getBod());
	}
	public JPanel txtPanel(JTextField txtField){
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.add(txtField);
		return panel;
	}
}

class DeleteContact extends JFrame{
	private JLabel lblTitle;
    private JLabel lblTopicSearch;
    private JLabel lblTopicUpdate;
    private JLabel lblId;
    private JLabel lblName;
    private JLabel lblPhone;
    private JLabel lblCompany;
    private JLabel lblSalary;
    private JLabel lblBod;
    private JLabel lblInvalid;
    private JLabel lblSearch;
    private JLabel lblError;
    private JLabel lblExistingId;
    

    private JButton btnDelete;
    private JButton btnCancel;
    private JButton btnSearchId;
    
    private JTextField txtSearchId;
    private JTextField txtName;
    private JTextField txtPhone;
    private JTextField txtCompany;
    private JTextField txtSalary;
    private JTextField txtBod;
    
    int intId;
    public DeleteContact(){
		setSize(900, 500);
        setTitle("Delete Contacts");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
		
		lblTitle = new JLabel("DELETE CONTACT");
        lblTitle.setFont(new Font("", 1, 30));
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        add(lblTitle, BorderLayout.NORTH);
        
		JPanel mainPanel = new JPanel(new FlowLayout());
		JPanel searchPanel = new JPanel(new GridLayout(1, 4, 3, 3));
		JPanel topicPanel = new JPanel(new FlowLayout());
        JPanel lblPanel = new JPanel(new GridLayout(1, 2, 3, 3));
		JPanel lblExistingPanel = new JPanel(new GridLayout(6, 2, 3, 3));
		JPanel lblUpdatePanel = new JPanel(new GridLayout(6, 2, 3, 3));
		
        JPanel searchnamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lblSearch = new JLabel("Name or Phone No: ");
        lblSearch.setFont(new Font("", 1, 20));
        searchnamePanel.add(lblSearch);
		searchPanel.add(searchnamePanel);

        txtSearchId = new JTextField(10);
        txtSearchId.setFont(new Font("", 1, 20));
        JPanel txtIdPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtIdPanel.add(txtSearchId);
        searchPanel.add(txtIdPanel);
		
		JPanel searchbtnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnSearchId = new JButton("Search");
        btnSearchId.setFont(new Font("", 1, 20));
        btnSearchId.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                intId = Example.contactList.searchUser(txtSearchId.getText());
                if (intId == -1) {
                    lblInvalid.setVisible(true);
                } else {
                    lblInvalid.setVisible(false);
					updateExistingContactLabels(intId);
                }
            }
        });
        searchbtnPanel.add(btnSearchId);
		searchPanel.add(searchbtnPanel);
		
        lblInvalid = new JLabel("<html><font color='red'>Not Found</font></html>");
        lblInvalid.setFont(new Font("", 1, 20));
        lblInvalid.setVisible(false);
        searchPanel.add(lblInvalid);

        mainPanel.add(searchPanel);
        
        add(mainPanel, BorderLayout.CENTER);

        JPanel txtPanel = new JPanel(new GridLayout(7, 2, 3, 3));
		
		lblId = new JLabel("ID");
        lblId.setFont(new Font("", 1, 20));
        txtPanel.add(lblId);
		
		lblExistingId = new JLabel();
        lblExistingId.setFont(new Font("", 1, 20));
        txtPanel.add(lblExistingId);
		
		lblName = new JLabel("Name");
        lblName.setFont(new Font("", 1, 20));
        txtPanel.add(lblName);
		
        txtName = new JTextField(10);
        txtName.setFont(new Font("", 1, 20));
        txtPanel.add(txtPanel(txtName));
		
		lblPhone = new JLabel("Phone");
        lblPhone.setFont(new Font("", 1, 20));
        txtPanel.add(lblPhone);
        
        txtPhone = new JTextField(10);
        txtPhone.setFont(new Font("", 1, 20));
        txtPanel.add(txtPanel(txtPhone));

		lblCompany = new JLabel("Company");
        lblCompany.setFont(new Font("", 1, 20));
        txtPanel.add(lblCompany);
		
        txtCompany = new JTextField(10);
        txtCompany.setFont(new Font("", 1, 20));
        txtPanel.add(txtPanel(txtCompany));
		
		lblSalary = new JLabel("Salary");
        lblSalary.setFont(new Font("", 1, 20));
        txtPanel.add(lblSalary);
        
        txtSalary = new JTextField(10);
        txtSalary.setFont(new Font("", 1, 20));
        txtPanel.add(txtPanel(txtSalary));
		
		lblBod = new JLabel("Birthday");
        lblBod.setFont(new Font("", 1, 20));
        txtPanel.add(lblBod);
        
        txtBod = new JTextField(10);
        txtBod.setFont(new Font("", 1, 20));
        txtPanel.add(txtPanel(txtBod));

       // lblPanel.add(txtPanel);
        
		mainPanel.add(txtPanel);
        add(mainPanel, BorderLayout.CENTER);
        
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        lblError = new JLabel();
        lblError.setFont(new Font("", 1, 15));
        lblError.setVisible(false);
        btnPanel.add(lblError);
        
        btnDelete = new JButton("Delete");
        btnDelete.setFont(new Font("", 1, 15));
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
				if (Example.contactList.getValid(intId)){
					Example.contactList.deleteUser(intId);
					lblError.setText("Contact Deleted Successfully");
					lblError.setVisible(true);
				}
            }
        });
        btnPanel.add(btnDelete);

        btnCancel = new JButton("Cancel");
        btnCancel.setFont(new Font("", 1, 15));
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
				DeleteContact.this.dispose();
			}
		});
		btnPanel.add(btnCancel);

		add("South",btnPanel);
	}
	
	public void updateExistingContactLabels(int contactId) {
		Contact contact = Example.contactList.get(contactId);
		lblExistingId.setText(contact.getId());
		txtName.setText(contact.getName());
		txtPhone.setText(contact.getPhone());
		txtCompany.setText(contact.getCompany());
		txtSalary.setText(String.valueOf(contact.getSalary()));
		txtBod.setText(contact.getBod());
	}
	
	public JPanel txtPanel(JTextField txtField){
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.add(txtField);
		return panel;
	}
}

class UpdateContact extends JFrame {
    //private ContactList contactList;

    private JLabel lblTitle;
    private JLabel lblTopicSearch;
    private JLabel lblTopicUpdate;
    private JLabel lblId;
    private JLabel lblName;
    private JLabel lblPhone;
    private JLabel lblCompany;
    private JLabel lblSalary;
    private JLabel lblBod;
    private JLabel lblInvalid;
    private JLabel lblSearch;
    private JLabel lblError;
    private JLabel lblExistingId;
    private JLabel lblExistingName;
    private JLabel lblExistingPhone;
    private JLabel lblExistingCompany;
    private JLabel lblExistingSalary;
    private JLabel lblExistingBod;

    private JButton btnAdd;
    private JButton btnCancel;
    private JButton btnSearchId;

    private JTextField txtSearchId;
    private JTextField txtName;
    private JTextField txtPhone;
    private JTextField txtCompany;
    private JTextField txtSalary;
    private JTextField txtBod;

    private int intId;

    public UpdateContact() {
		setSize(900, 500);
		setTitle("Update Contacts");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);

		lblTitle = new JLabel("UPDATE CONTACT");
		lblTitle.setFont(new Font("", 1, 30));
		lblTitle.setHorizontalAlignment(JLabel.CENTER);
		add(lblTitle, BorderLayout.NORTH);

		JPanel mainPanel = new JPanel(new FlowLayout());
		JPanel searchPanel = new JPanel(new GridLayout(1, 4, 3, 3));
		//JPanel topicPanel = new JPanel(new GridLayout(1, 2, 3, 3));
        JPanel lblPanel = new JPanel(new GridLayout(1, 2, 3, 3));
		JPanel lblExistingPanel = new JPanel(new GridLayout(6, 2, 3, 3));
		JPanel lblUpdatePanel = new JPanel(new GridLayout(6, 2, 3, 3));

        JPanel searchnamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lblSearch = new JLabel("Name or Phone No: ");
        lblSearch.setFont(new Font("", 1, 20));
        searchnamePanel.add(lblSearch);
		searchPanel.add(searchnamePanel);

        txtSearchId = new JTextField(10);
        txtSearchId.setFont(new Font("", 1, 20));
        JPanel txtIdPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtIdPanel.add(txtSearchId);
        searchPanel.add(txtIdPanel);
		
		JPanel searchbtnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnSearchId = new JButton("Search");
        btnSearchId.setFont(new Font("", 1, 20));
        btnSearchId.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                intId = Example.contactList.searchUser(txtSearchId.getText());
                if (intId == -1) {
                    lblInvalid.setVisible(true);
                } else {
                    lblInvalid.setVisible(false);
                    updateExistingContactLabels(intId);
                }
            }
        });
        searchbtnPanel.add(btnSearchId);
		searchPanel.add(searchbtnPanel);
		
        lblInvalid = new JLabel("<html><font color='red'>Not Found</font></html>");
        lblInvalid.setFont(new Font("", 1, 20));
        lblInvalid.setVisible(false);
        searchPanel.add(lblInvalid);

        mainPanel.add(searchPanel);

        JPanel txtPanel = new JPanel(new GridLayout(7, 2, 3, 3));
		
		lblId = new JLabel("ID");
        lblId.setFont(new Font("", 1, 20));
        txtPanel.add(lblId);
		
		lblExistingId = new JLabel();
        lblExistingId.setFont(new Font("", 1, 20));
        txtPanel.add(lblExistingId);
		
		lblName = new JLabel("Name");
        lblName.setFont(new Font("", 1, 20));
        txtPanel.add(lblName);
		
        txtName = new JTextField(10);
        txtName.setFont(new Font("", 1, 20));
        txtPanel.add(txtPanel(txtName));
		
		lblPhone = new JLabel("Phone");
        lblPhone.setFont(new Font("", 1, 20));
        txtPanel.add(lblPhone);
        
        txtPhone = new JTextField(10);
        txtPhone.setFont(new Font("", 1, 20));
        txtPanel.add(txtPanel(txtPhone));

		lblCompany = new JLabel("Company");
        lblCompany.setFont(new Font("", 1, 20));
        txtPanel.add(lblCompany);
		
        txtCompany = new JTextField(10);
        txtCompany.setFont(new Font("", 1, 20));
        txtPanel.add(txtPanel(txtCompany));
		
		lblSalary = new JLabel("Salary");
        lblSalary.setFont(new Font("", 1, 20));
        txtPanel.add(lblSalary);
        
        txtSalary = new JTextField(10);
        txtSalary.setFont(new Font("", 1, 20));
        txtPanel.add(txtPanel(txtSalary));
		
		lblBod = new JLabel("Birthday");
        lblBod.setFont(new Font("", 1, 20));
        txtPanel.add(lblBod);
        
        txtBod = new JTextField(10);
        txtBod.setFont(new Font("", 1, 20));
        txtPanel.add(txtPanel(txtBod));

       // lblPanel.add(txtPanel);
        
		mainPanel.add(txtPanel);
		add(mainPanel, BorderLayout.CENTER);
		
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        lblError = new JLabel("Invalid Input");
        lblError.setFont(new Font("", 1, 15));
        lblError.setVisible(false);
        btnPanel.add(lblError);
        
        btnAdd = new JButton("Update");
        btnAdd.setFont(new Font("", 1, 15));
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                updateContact();
            }
        });
        btnPanel.add(btnAdd);

        btnCancel = new JButton("Cancel");
        btnCancel.setFont(new Font("", 1, 15));
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
				UpdateContact.this.dispose();
			}
		});
		btnPanel.add(btnCancel);

		add("South",btnPanel);
	}
	
	public JPanel txtPanel(JTextField txtField){
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.add(txtField);
		return panel;
	}

	public void updateExistingContactLabels(int contactId) {
		Contact contact = Example.contactList.get(contactId);
		lblExistingId.setText(contact.getId());
		txtName.setText(contact.getName());
		txtPhone.setText(contact.getPhone());
		txtCompany.setText(contact.getCompany());
		txtSalary.setText(String.valueOf(contact.getSalary()));
		txtBod.setText(contact.getBod());
	}

	private void updateContact() {
		if (intId == -1) {
			lblInvalid.setVisible(true);
			return;
		}

		String name = txtName.getText();
		String phoneNo = txtPhone.getText();
		String company = txtCompany.getText();
		double salary = Double.parseDouble(txtSalary.getText());
		String bod = txtBod.getText();
	
		if(salary<0 || (phoneNo.charAt(0)!='0' | phoneNo.length() !=10) || !Example.checkDate(bod)){
			lblError.setVisible(true);
		}else{ 
			Contact contact = Example.contactList.get(intId);
			contact.setName(name);
			contact.setPhone(phoneNo);
			contact.setCompany(company);
			contact.setSalary(salary);
			contact.setBod(bod);
			lblError.setVisible(false);
		}
	}
}



class AddContacts extends JFrame {
	ContactList contactList;
	private JLabel lblTitle;
	private JLabel lblId;
	private JLabel lblShowId;
	private JLabel lblName;
	private JLabel lblPhone;
	private JLabel lblCompany;
	private JLabel lblSalary;
	private JLabel lblBod;
	private JLabel lblError;
	
	private JButton btnAdd;
	private JButton btnCancel;
	
	private JTextField txtName;
	private JTextField txtPhone;
	private JTextField txtCompany;
	private JTextField txtSalary;
	private JTextField txtBod;
	
	AddContacts(){
		setSize(400, 400);
		setTitle("Add Contacts");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		lblTitle = new JLabel("Add Contact");
		lblTitle.setFont(new Font("",1,30));
		lblTitle.setHorizontalAlignment(JLabel.CENTER);
		add("North",lblTitle);
		
		JPanel lblPanel = new JPanel(new GridLayout(7,2,3,3));
		
		lblId = new JLabel("ID");
		lblId.setFont(new Font("",1,20));
		lblPanel.add(lblId);
		
		lblShowId = new JLabel(Example.generateId());
		lblShowId.setFont(new Font("",1,20));
		lblPanel.add(lblShowId);
		
		lblName = new JLabel("Name");
		lblName.setFont(new Font("",1,20));
		lblPanel.add(lblName);
			
		txtName = new JTextField(10);
		txtName.setFont(new Font("",1,20));
		JPanel txtNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		txtNamePanel.add(txtName);
		lblPanel.add(txtNamePanel);
		
		lblPhone = new JLabel("Phone");
		lblPhone.setFont(new Font("",1,20));
		lblPanel.add(lblPhone);
		
		txtPhone = new JTextField(10);
		txtPhone.setFont(new Font("",1,20));
		JPanel txtPhonePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		txtPhonePanel.add(txtPhone);
		lblPanel.add(txtPhonePanel);
		
		lblCompany = new JLabel("Company");
		lblCompany.setFont(new Font("",1,20));
		lblPanel.add(lblCompany);
		
		txtCompany = new JTextField(10);
		txtCompany.setFont(new Font("",1,20));
		JPanel txtCompanyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		txtCompanyPanel.add(txtCompany);
		lblPanel.add(txtCompanyPanel);
		
		lblSalary = new JLabel("Salary");
		lblSalary.setFont(new Font("",1,20));
		lblPanel.add(lblSalary);
		
		txtSalary = new JTextField(6);
		txtSalary.setFont(new Font("",1,20));
		JPanel txtSalaryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		txtSalaryPanel.add(txtSalary);
		lblPanel.add(txtSalaryPanel);
		
		lblPhone = new JLabel("Birthday");
		lblPhone.setFont(new Font("",1,20));
		lblPanel.add(lblPhone);
			
		txtBod = new JTextField(6);
		txtBod.setFont(new Font("",1,20));
		JPanel txtBodPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		txtBodPanel.add(txtBod);
		lblPanel.add(txtBodPanel);
		
		lblError = new JLabel();
		lblError.setFont(new Font("",1,15));
		lblError.setVisible(false);
		lblPanel.add(lblError);
		
		add(lblPanel);
		
		JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("",1,15));
		btnAdd.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent evt){
			String id = Example.generateId();
			String name = txtName.getText();
			String phoneNo = txtPhone.getText();
			String company = txtCompany.getText();
			double salary = Double.parseDouble(txtSalary.getText());
			String bod = txtBod.getText();
			if(salary<0 || (phoneNo.charAt(0)!='0' | phoneNo.length() !=10) || !Example.checkDate(bod)){
				lblError.setText("<html><font color='red'>Invalid Input</font></html>");
				lblError.setVisible(true);
			}else{
				Contact contact = new Contact (id, name, phoneNo, company, salary, bod);
				Example.contactList.add(contact);
				lblError.setText("<html><font color='blue'>Contact Added</font></html>");
				lblError.setVisible(true);
			}
			}
		});
			
		btnPanel.add(btnAdd);
			
		btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("",1,15));
		btnCancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				AddContacts.this.dispose();
			}
		});	

		btnPanel.add(btnCancel);
		add("South", btnPanel);
		
	}
}


class Example{
	public static ContactList contactList = new ContactList();
	
	public static void main(String args[]){
		new ContactMainForm().setVisible(true);
		
		
	}
	
	public static String generateId(){
		int id = contactList.getSize();
		return String.format("C%04d",id+1);
	}

	public static boolean checkDate(String bod){
		LocalDate currentDate = LocalDate.now();
		int currentMonthValue = currentDate.getMonthValue();
		int currentYear=currentDate.getYear();    
		int currentMonthDate=currentDate.getDayOfMonth();
		
		//0123456789
		//YYYY-MM-DD
		int bodYear;
		int bodMonth;
		int bodDate;
		
		//---- Check the length of the birthday
		if(bod.length()==10){
			
			//---- Check the characters of the birthday
			char[] tempChar = {'0','1','2','3','4','5','6','7','8','9','-'};
			boolean charinit = false;
			for (int i =0; i<bod.length(); i++){
				for (int j=0; j<tempChar.length; j++){
					if(bod.charAt(i)==tempChar[j]){
						charinit = true;
						break;
					}
				}
				if (charinit == true){
					continue;
				}
				else{
					System.out.println("\n\t Incorrect Birthday format...\n");
					return false;
				}
			}
			if(bod.charAt(4) == '-' & bod.charAt(7)=='-'){
				//---- Check maximum age that can apply. If user is born before 1900 cannot register. It is an wrong input.
				if ((Integer.parseInt(bod.substring(0,4)) > 1900) & (Integer.parseInt(bod.substring(5,7))<=12) & (Integer.parseInt(bod.substring(8))<=31)){
					bodYear = Integer.parseInt(bod.substring(0,4));
					bodMonth = Integer.parseInt(bod.substring(5,7));
					bodDate = Integer.parseInt(bod.substring(8,10));
				} else {
					System.out.println("\n\t Incorrect Birthday format...\n");
					return false;
				}
			}else{
				System.out.println("\n\t Incorrect Birthday format...\n");
				return false;
			}
		} else {
			System.out.println("\n\t Incorrect Birthday format...\n");
			return false;
		}
		
		//---- Check the user born before current date
		boolean isValid  = false;		
		do {
			if (bodYear<currentYear){
				return true;
			} else if (bodYear>currentYear){
				System.out.println("\n\t Incorrect Birthday...\n");
				return false;
			}else {
				if (bodMonth <currentMonthValue){
					return true;
				}else if (bodMonth==currentMonthValue){
					if (bodDate<currentMonthDate){
						return true;
					}else{
						System.out.println("\n\t Incorrect Birthday...\n");
						return false;
					}
				} else {
					System.out.println("\n\t Incorrect Birthday...\n");
					return false;
				}
			}
		} while (isValid);
	}
	
	
	//---------------------------------- Header section
	public static void header(){
		System.out.println();
		System.out.println("\t /$$ /$$$$$$$$ /$$$$$$$  /$$$$$$ /$$$$$$$$ /$$   /$$ /$$$$$$$");
		System.out.println("\t |__/| $$_____/| $$__  $$|_  $$_/| $$_____/| $$$ | $$| $$__  $$");
		System.out.println("\t  /$$| $$      | $$  \\ $$  | $$  | $$      | $$$$| $$| $$  \\ $$");
		System.out.println("\t | $$| $$$$$   | $$$$$$$/  | $$  | $$$$$   | $$ $$ $$| $$  | $$");
		System.out.println("\t | $$| $$__/   | $$__  $$  | $$  | $$__/   | $$  $$$$| 44  | $$");
		System.out.println("\t | $$| $$      | $$  \\ $$  | $$  | $$      | $$\\  $$$| $$  | $$");
		System.out.println("\t | $$| $$      | $$  | $$ /$$$$$$| $$$$$$$$| $$ \\  $$| $$$444$/");
		System.out.println("\t |__/|__/      |__/  |__/|______/|________/|__/  \\__/|_______/");
		
		System.out.println("\t ");
		System.out.println("\t ");
		
		System.out.println("   _____\t\t\t\t     ____\t\t\t _");
		System.out.println("  / ____|\t    _\t\t  _\t    / __ \\ \t\t        (_)");
		System.out.println(" | |     ___  _ __ | |_ __ _  ___| |_ ___  | |  | |_ __ __ _  __ _ _ __  _ _______ _ __");
		System.out.println(" | |    / _ \\| `_ \\| __/ _` |/ __| __/ __| | |  | | `__/ _  |/ _` | `_ \\| |_  / _ \\ `__|");
		System.out.println(" | |___| (_) | | | | || |_| | |__| |_\\__ \\ | |__| | | | |_| | |_| | | | | |/ /  __/ |");
		System.out.println("  \\_____\\___/|_| |_|\\__\\__,_|\\___|\\__|___/  \\____/|_|  \\__, |\\__,_|_| |_|_/___\\___|_|");
		System.out.println("\t\t\t\t\t\t        __/ | ");
		System.out.println("\t\t\t\t\t\t       |___/");
		System.out.println("\n=========================================================================================\n");
		
	}
	
	//---------------------- Exit method ----------------------------------
	public static void exit(){
		
		header();
		
		System.out.println("\t\t  _____  _    _  _______ _______ 	");
		System.out.println("\t\t |  ___|\\ \\  / /|__   __|__   __| ");
		System.out.println("\t\t | |__   \\ \\/ /    | |     | |	");
		System.out.println("\t\t |  __|   >  <     | |     | |		");
		System.out.println("\t\t | |___  / /\\ \\  __| |__   | |	");
		System.out.println("\t\t |_____|/_/  \\_\\|_______|  |_|	");
		System.out.println("\t     =======================================");
		System.exit(0);
	}
}
