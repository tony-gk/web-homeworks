<template>
    <Form :form="form"/>
</template>

<script>
    import Form from "../templates/FormTemplate";

    export default {
        name: "Enter",
        components: {
            Form
        },
        data: function () {
            return {
                form: {
                    header: "Enter",
                    fields: {
                        login: {id: 1, fieldName: "Login", name: "login", value: ""},
                        password: {id: 2, fieldName: "Password", name: "password", value: ""}
                    },
                    error: "",
                    button: {
                        name: "Enter",
                        prevent: this.onEnter
                    }
                }
            }
        },
        beforeCreate() {
            this.$root.$on("onEnterValidationError", (error) => {
                this.form.error = error;
            });
        },
        beforeMount() {
            this.form.fields.login.value = "";
            this.form.fields.password.value = "";
            this.form.error = "";
        },
        methods: {
            onEnter: function () {
                this.$root.$emit("onEnter",
                    this.form.fields.login.value,
                    this.form.fields.password.value);
            }
        }
    }
</script>

<style scoped>

</style>
