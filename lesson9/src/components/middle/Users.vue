<template>
    <div class="datatable">
        <div class="caption">Users</div>
        <table>
            <tr>
                <th>id</th>
                <th>name</th>
                <th>login</th>
            </tr>
            <tr v-for="user in viewUsers" :key="user.id">
                <td>{{user.id}}</td>
                <td>{{user.name}}</td>
                <td>{{user.login}}</td>
            </tr>
        </table>
    </div>
</template>

<script>
    export default {
        name: "Users",
        props: ['users'],
        computed: {
            viewUsers: function () {
                return Object.values(this.users);
            }
        }

    }
</script>

<style scoped>

</style>