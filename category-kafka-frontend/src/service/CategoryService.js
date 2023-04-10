import axios from "axios"

const base_url = "http://localhost:8080/categories/";

class CategoryService {

    addCategory(category) {
        axios.post(base_url + "add", category)
            .then((res) => {
                console.log(res.data);
            }).catch(function (error) {
            console.log(error);
        })
    }
}
export default new CategoryService();