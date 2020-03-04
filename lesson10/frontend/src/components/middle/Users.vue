<template>
    <div class="datatable">
        <div class="caption">Users</div>
        <table>
            <tr>
                <th>id</th>
                <th>name</th>
                <th>login</th>
            </tr>
            <tr v-for="user in users" :key="user.id">
                <td>{{user.id}}</td>
                <td>{{user.name}}</td>
                <td>{{user.login}}</td>
            </tr>
        </table>
    </div>
</template>

<script>
    import axios from "axios";

    export default {
        name: "Users",
        data: function() {
            return {
                users: []
            }
        },
        mounted() {
            axios.get("/api/1/users").then(response => {
                this.users = response.data
            })
        }


    }
</script>

<style scoped>

</style>