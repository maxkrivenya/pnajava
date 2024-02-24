import React, { useEffect, useState } from 'react';
import './App.css';
import ava from './ava.png';
function App() {
  return (
    <div className="App">

        <header className="App-header">
          <div className={"textBox"}>
            IIS BSUIR:UNIVERSITY
          </div>
      </header>

        <div className={"App-sidebar"}>

        </div>
        <div className={"App-body"}>
          <UserById />
        </div>
    </div>
  );
}

/*function MarksList(id) {
        const [data, setData] = useState([]);
        useEffect(() => {
            fetch('http://localhost:8080/api/marks/' + id)
                .then((res) => {
                    return res.json();
                })
                .then((data) => {
                    console.log(data);
                    setData(data);
                });
        }, []);
        return (
            <table className="studentList">
                {data.map((value) => (
                            <tr key={value.subj}>
                                <td>{value.mark}</td>
                                <td>{value.date}</td>
                            </tr>
                ))}
            </table>
        );
}*/

function UserById() {
    const [data, setData] = useState([]);
    useEffect(() => {
        fetch('http://localhost:8080/api/student/22070099')
            .then((res) => {
                return res.json();
            })
            .then((data) => {
                console.log(data);
                setData(data);
            });
    }, []);
    return (
        <div className={"flexRow"}>
            <img src={ava}  alt={"dead"}/>

            <div className={"flexColumn"}>
                <div className={"textBox"}>
                    {data.name}
                </div>
                <div className={"textBox"}>
                    {data.fac} {data.spec} {data.group}
                </div>
            </div>
        </div>

    );
}
export default App;
