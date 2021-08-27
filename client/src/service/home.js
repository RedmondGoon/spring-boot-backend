import axios from "axios";

export async function getUserProfile() {
    try {
        const res = await axios.get(
            "http://localhost:8080/holdings/Home?accId=1"
        );
        // const res = await axios.get("data/homeData.json");
        return res.data;
    } catch (err) {
        return err;
    }
}
