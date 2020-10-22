<template>
    <div>
        <h1>Watcher Example</h1>
        <input type="text" v-model="question" />
        <div>Old Value: {{ oldVal }}</div>
        <div>New Value: {{ newVal }}</div>
    </div>
</template>

<script>
export default {
    name: 'watcher',
    data() {
        return {
            question: '',
            oldVal: '',
            newVal: ''
        }
    },
    watch: {
        question: function( newValue, oldValue )  {
            this.oldVal = oldValue;
            this.newVal = newValue;
        }
    }
}
</script>

<style>

</style>