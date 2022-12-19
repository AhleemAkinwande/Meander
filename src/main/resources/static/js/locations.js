window.addEventListener("DOMContentLoaded", function() {

         fetch("https://countriesnow.space/api/v0.1/countries/states",
                     {
                     method:'POST',
                     headers: {'Content-Type': 'application/json'},
                     body: JSON.stringify({country: "United States"})})
                         .then(res => res.json())
                         .then(data => {
                             const states = data.data.states.map(i => i.name).sort();
                             document.querySelector("#state").innerHTML = `<option selected disabled>Select a state</option>` + states.map(state => `<option value='${state}'>${state}</option>`).join("")


                        document.querySelector("#state").addEventListener("change", function(e) {

                            document.querySelector("#city").value="";

                            fetch("https://countriesnow.space/api/v0.1/countries/state/cities",
                                                 {
                                                 method:'POST',
                                                 headers: {'Content-Type': 'application/json'},
                                                 body: JSON.stringify({country: "United States", state: e.target.value})
                                                 })
                                                     .then(res => res.json())
                                                     .then(data => {
                                                         const cities = data.data.sort();
                                                         document.querySelector("#city-list").innerHTML = cities.map(city => `<option value='${city}'>`).join("")

                                                      })
                                                 })
                                             })

                                             })




