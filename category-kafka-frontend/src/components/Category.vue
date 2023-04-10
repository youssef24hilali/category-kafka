<template>

  <div class="container">
    <div class="signup-content">
      <form @submit.prevent="addCategory" method='post' class="signup-form">
        <h2 class="form-title" style="margin-bottom: 60px; margin-top: 20px">Add Category</h2>
        <div class="form-group position-relative">
          <label for="nom">Category Name<span style="color: red;">*</span></label>
          <input autocomplete="off" ref="nameInput" :class="{ 'is-invalid': isNameInvalid && touched }" v-model="category.name" type="text" class="form-control" name="nom" id="name" placeholder="Category Name"/>
          <div class="invalid-feedback" v-if="formErrors[0] !== null">
            {{ formErrors[0] }}
          </div>
        </div>
        <div class="form-group">
          <input type="submit" name="submit" id="submit" class="btn btn-primary send-button" value="Add Category"/>
        </div>
      </form>
    </div>
  </div>

</template>

<script>
import CategoryService from "@/service/CategoryService";

export default {
  name: "CategoryTable",
  data(){
    return{
      formErrors: [],
      touched: false,
      category: {
        name: ""
      }
    }
  },
  methods: {
    addCategory() {

      const nameInput = this.$refs.nameInput;

      this.formErrors = [];

      if (!this.category.name) {
        this.formErrors.push("Category name can't be empty");
        nameInput.classList.add('is-invalid');
      } else {
        this.formErrors.push("");
        nameInput.classList.remove('is-invalid');
      }

      this.touched = true;

      if (this.formErrors[0] === "") {
        CategoryService.addCategory({
          name: this.category.name
        })
      }
    }
  },
  computed: {
    isNameInvalid() {
      return !this.category.name;
    }
  }
}
</script>

<style scoped>

form {
  width: 500px;
  margin: 20px auto;
}

.form-group {
  margin-bottom: 20px;
}

input[type="text"]{
  padding: 10px 15px;
  background-color: #efefef;
  border: 1px solid #ccc;
  width: 100%;
  border-radius: 5px;
}

input[type="text"].is-invalid{
  background-color: #fdeeee;
  border-color: #ff4343;
}

.form-control {
  padding: 10px 15px;
  background-color: #efefef;
  border: 1px solid #ccc;
  border-radius: 5px;
  width: 100%;
}

label {
  display: block;
  margin-bottom: 15px;
  font-size: 18px;
}

.btn.send-button {
  display: block;
  height: 50px;
  border-color: transparent;
  padding: 5px 20px;
  width: 100%;
  font-size: 24px;
}
</style>