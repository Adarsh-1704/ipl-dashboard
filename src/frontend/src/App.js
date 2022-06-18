import logo from './logo.svg';
import './App.css';
import {TeamPage} from './pages/TeamPage';
import {MatchPage} from './pages/MatchPage';
import {HomePage} from './pages/HomePage';
import {HashRouter as Router,Routes, Route} from 'react-router-dom';

function App() {
  return (
    <div className="App">
      <Router>
          <Routes>
                <Route path="/team/:teamName" element = {<TeamPage/>}></Route>
                <Route path="/team/:teamName/year/:year" element = {<MatchPage/>}></Route>
                <Route path="/" element = {<HomePage/>}></Route>
          </Routes>
      </Router>
    </div>
  );
}

export default App;