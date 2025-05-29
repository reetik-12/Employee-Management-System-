const apiBase = "http://localhost:8080";
const tbody = document.querySelector('#employeeTable tbody');
const modal = document.getElementById("employeeModal");
const closeBtn = document.querySelector(".close");
const form = document.getElementById("employeeForm");
const addEmployeeBtn = document.getElementById("addEmployeeBtn");
const formTitle = document.getElementById("formTitle");

let editingId = null;

const fetchEmployees = async () => {
  const res = await fetch(`${apiBase}/employees`);
  const data = await res.json();
  tbody.innerHTML = "";
  data.forEach(emp => {
    const row = `<tr>
      <td>${emp.id}</td>
      <td>${emp.name}</td>
      <td>${emp.email}</td>
      <td>${emp.salary}</td>
      <td>${emp.mobno}</td>
      <td>
        <button class="update-btn" onclick='openForm(${JSON.stringify(emp)})'>Update</button>
        <button class="delete-btn" onclick='deleteEmployee(${emp.id})'>Delete</button>
      </td>
    </tr>`;
    tbody.insertAdjacentHTML("beforeend", row);
  });
};

const openForm = (emp = null) => {
  modal.style.display = "block";
  if (emp) {
    formTitle.innerText = "Update Employee";
    document.getElementById("empId").value = emp.id;
    document.getElementById("name").value = emp.name;
    document.getElementById("email").value = emp.email;
    document.getElementById("salary").value = emp.salary;
    document.getElementById("mobno").value = emp.mobno;
    editingId = emp.id;
  } else {
    formTitle.innerText = "Add Employee";
    form.reset();
    editingId = null;
  }
};

const deleteEmployee = async (id) => {
  await fetch(`${apiBase}/employee/delete/${id}`, { method: "DELETE" });
  fetchEmployees();
};

form.onsubmit = async (e) => {
  e.preventDefault();
  const emp = {
    name: form.name.value,
    email: form.email.value,
    salary: form.salary.value,
    mobno: form.mobno.value
  };
  if (editingId) {
    await fetch(`${apiBase}/employee/update/${editingId}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(emp)
    });
  } else {
    await fetch(`${apiBase}/employee/add`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(emp)
    });
  }
  modal.style.display = "none";
  fetchEmployees();
};

closeBtn.onclick = () => modal.style.display = "none";
addEmployeeBtn.onclick = () => openForm();
window.onclick = (event) => { if (event.target === modal) modal.style.display = "none"; }

fetchEmployees();
