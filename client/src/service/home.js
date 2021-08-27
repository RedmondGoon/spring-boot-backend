import axios from "axios";

export async function getUserProfile() {
    try {
        const res = await axios.get("data/homeData.json");
        return res.data;
    } catch (err) {
        return err;
    }
}
