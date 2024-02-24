import React, { useEffect, useState } from 'react';
import logo from './logo.svg';
import './App.css';
import './api-hell.css'
function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
          <UsersList />
      </header>
    </div>
  );
}
function UsersList() {
        const [data, setData] = useState([]);
        useEffect(() => {
            fetch('http://localhost:8080/api/student')
                .then((res) => {
                    return res.json();
                })
                .then((data) => {
                    console.log(data);
                    setData(data);
                });
        }, []);
        return (
            <div>
                <ul className="studentList">
                {data.map((value) => (
                    <li key={value.id}>{value.id + ' | ' + value.name + ' | ' + value.fac}</li>
                ))}
                </ul>
            </div>
        );
}
export default App;
