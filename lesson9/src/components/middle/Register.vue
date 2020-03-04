<template>
    <Form :form="form"/>
</template>

<script>
    import Form from "../templates/FormTemplate";

    export default {
        name: "Register",
        components: {Form},
        data: function () {
            return {
                form: {
                    header: "Register",
                    fields: {
                        login: {id: 1, fieldName: "Login", name: "login", value: ""},
                        name: {id: 2, fieldName: "Name", name: "name", value: ""}
                    },
                    error: "",
                    button: {
                        name: "Register",
                        prevent: this.onRegister
                    }
                }

            }
        },
        beforeCreate() {
            this.$root.$on("onRegisterValidationError", (error) => {
                this.form.error = error;
            });
        },
        methods: {
            onRegister: function () {
                this.$root.$emit("onRegister",
                    this.form.fields.login.value,
                    this.form.fields.name.value)
            }
        }
    }
</script>

<style scoped>

</style>
