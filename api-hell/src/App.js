import React, { useEffect, useState } from 'react';
import './App.css';
import ava from './ava.png';
import logo from './logo.png';
function App() {
    return (
        <div className="App">
            <img src={logo} alt={"iis"} style={{position: "absolute", width: "80px", height: "80px", zIndex: "100"}}></img>
            <div className={"flexRow"} style={{width: "100%"}}>
                <header className="App-header">
                    <div className={"textBox"}>
                        IIS BSUIR:UNIVERSITY
                    </div>
                    <div className={"flexRow"} style={{paddingTop: "10px"}}>
                        <div className={"myButton"}>Find</div>
                        <input
                            className={"searchBar"} inputMode={"text"} defaultValue={"Input ID"}>
                        </input>
                    </div>
                </header>
            </div>

            <div className={"App-sidebar"}>
            </div>

            <div className={"App-body"}>
                <UserById/>
                <MarksList/>
            </div>
        </div>
    );
}

function MarksForSubjectInTable({subj}) {
    const [data, setData] = useState([]);
    useEffect(() => {
        fetch('http://localhost:8080/api/student/25050071/marks/' + subj)
            .then((res) => {
                try{
                    if(res.status===204){
                        return [];
                    }
                    return res.json();
                }
                catch(err){
                    return ("");
                };
            })
            .then((data) => {
                setData(data);
            });
    }, [subj]);
    if(data===undefined || data===[]){
        return (<td></td>);
    }
    console.log(data);
    return (
        data.map((mark) => (
            <td>
                {mark}
            </td>
        ))
    );
}

function MarksList() {
    const [subj, setSubj] = useState([]);
    useEffect(() => {
        fetch('http://localhost:8080/api/student/25050071/subjects')
            .then((res) => {
                return res.json();
            })
            .then((subj) => {
                setSubj(subj);
            });
    }, []);

    return (
        <table>
            <tbody>
            {
                subj.map(subject => (
                    <tr>
                        <td>
                            <div className={"textBox"}>
                                {subject}
                            </div>
                        </td>
                        <MarksForSubjectInTable subj = {subject}></MarksForSubjectInTable>
                    </tr>
                ))
            }
            </tbody>
        </table>
    );
}

function UserById() {
    const [data, setData] = useState([]);
    useEffect(() => {
        fetch('http://localhost:8080/api/student/25050071')
            .then((res) => {
                return res.json();
            })
            .then((data) => {
                setData(data);
            });
    }, []);
    return (
        <div className={"flexRow"} style={{width:"100%"}}>
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


/*
    let date1, date2
    for each subject where subj.sem=student.sem && subj.spec=stud.spec
        for each typeOfLecture
            SELECT value FROM marks WHERE marks.stud-id = student.id
                                      AND marks.subject = subject.name
                                      AND marks.type    = typeOfLecture
                                      AND marks.when    > date1
                                      AND marks.when    < date2

 */