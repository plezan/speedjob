<template>
  <v-dialog max-width="500px">
    <v-card>
      <v-card-title>
        CV
      </v-card-title>
      <v-card-text>
        <embed src="http://example.com/the.pdf" width="500" height="375" type='application/pdf'>
      </v-card-text>
      <v-card-actions>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
export default {
  name: 'pdf-printer'
}
</script>

<style scoped>

</style>
