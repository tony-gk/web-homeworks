<template>
    <div class="form-box">
        <div class="header">{{form.header}}</div>
        <div class="body">
            <form @submit.prevent="form.button.prevent">

                <div class="field" v-for="field in formFields" :key="field.id">
                    <div class="name">
                        <label :for="field.id">{{field.fieldName}}</label>
                    </div>
                    <div class="value">
                        <input :id="field.id" :name="field.name" v-model="field.value"/>
                    </div>
                </div>

                <div class="error">{{form.error}}</div>

                <div class="button-field">
                    <input type="submit" :value="form.button.name">
                </div>
            </form>
        </div>
    </div>
</template>

<script>
    export default {
        name: "Form",
        props: ['form'],
        computed: {
            formFields: function() {
                return Object.values(this.form.fields);
            }
        }
    }
</script>

<style scoped>

</style>