export async function getTrades() {
    const rows = [
        {"name": "AAPL", "qty":"159", "status":"FILLED", "price":"25", "amt":"3.0"}, 
        {"name": "IDM", "qty":"34", "status":"FILLED", "price":"34", "amt":"3.0"},
        {"name": "SE", "qty":"342", "status":"FILLED", "price":"25", "amt":"5.0"},
    ];

    return rows;
}