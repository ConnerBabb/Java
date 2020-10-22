<template>
  <table id="tblUsers">
    <thead>
    <tr>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Username</th>
        <th>Email Address</th>
        <th>Status</th>
    </tr>
    </thead>
    <tbody>
      <tr>
        <td><input type="text" id="firstNameFilter" v-model.trim="filter.firstName"/></td>
        <td><input type="text" id="lastNameFilter" v-model.trim="filter.lastName"/></td>
        <td><input type="text" id="usernameFilter" v-model.trim="filter.username"/></td>
        <td><input type="text" id="emailFilter" v-model.trim="filter.emailAddress"/></td>
        <td>
          <select id="statusFilter" v-model.trim="filter.status">
            <option value="">Show All</option>
            <option value="Active">Active</option>
            <option value="Disabled">Disabled</option>
          </select>
        </td>
      </tr> 
        <tr v-bind:class="{'disabled' : user.status === 'Disabled'}" class="user" 
        v-for="user in filteredList"  :key="user.id"  >
          <td>{{user.firstName}}</td>
          <td>{{user.lastName}}</td>
          <td>{{user.username}}</td>
          <td>{{user.emailAddress}}</td>
          <td>{{user.status}}</td>
        </tr>
    </tbody>
  </table>
</template>

<script>
export default {
  name: 'user-list',
  
  data() {
    return {
      vueProp : 'isFIlteredList',
      filter:[
        {firstName: "",lastName:'',userName:'',emailAddress:'',status:''}
        ],
      users: [
        { firstName: 'John', lastName: 'Smith', username: 'jsmith', emailAddress: 'jsmith@gmail.com', status: 'Active' },
        { firstName: 'Anna', lastName: 'Bell', username: 'abell', emailAddress: 'abell@yahoo.com', status: 'Active' },
        { firstName: 'George', lastName: 'Best', username: 'gbest', emailAddress: 'gbest@gmail.com', status: 'Disabled' },
        { firstName: 'Ben', lastName: 'Carter', username: 'bcarter', emailAddress: 'bcarter@gmail.com', status: 'Active' },
        { firstName: 'Katie', lastName: 'Jackson', username: 'kjackson', emailAddress: 'kjackson@yahoo.com', status: 'Active' },
        { firstName: 'Mark', lastName: 'Smith', username: 'msmith', emailAddress: 'msmith@foo.com', status: 'Disabled' }
      ]
    }
  },
  computed: {
    filteredList() {
      return this.users.filter( user => {
        if (this.filter.firstName) {
          if(!(user.firstName.toLowerCase().indexOf(this.filter.firstName.toLowerCase()) >= 0) )
          return  false;
        }
        if (this.filter.lastName) {
          if(!(user.lastName.toLowerCase().indexOf(this.filter.lastName.toLowerCase()) >= 0) )
          return  false;
        }
        if (this.filter.username) {
          if(!(user.username.toLowerCase().indexOf(this.filter.username.toLowerCase()) >= 0) )
          return  false;
        }
        if (this.filter.emailAddress) {
          if(!(user.emailAddress.toLowerCase().indexOf(this.filter.emailAddress.toLowerCase()) >= 0) )
          return  false;
        }
        if (this.filter.status) {
          if(!(user.status.toLowerCase().indexOf(this.filter.status.toLowerCase()) >= 0) )
          return  false;
        }
        return true;
      });
    }
  }
}
</script>

<style scoped>
table {
  margin-top: 20px;
  font-family:-apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif
}
th {
  text-transform: uppercase
}
td {
  padding: 10px;
}
tr.disabled {
  color: red;
}
input, select {
  font-size: 16px;
}
</style>
