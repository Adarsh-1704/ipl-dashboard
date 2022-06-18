import {TeamTile} from '../components/TeamTile';
import {React,useEffect,useState} from 'react';
import './HomePage.scss';

export function HomePage() {
    const [teamList,setTeamList] = useState([]);
    useEffect(
    ()=>{
        const getTeamData = async()=>{
            const response = await fetch(`/team/findAllTeams`);
            const data = await response.json();
            setTeamList(data);
        }

        getTeamData();
    },[]
    )

    return (
        <div className="HomePage">
            <div className="header-section">
                <h1 className="app-name">IPL Dashboard</h1>
            </div>
            <div className="team-grid">
                <h1> {teamList.map(m=><TeamTile key={m.id} teamName={m.teamName}/>)} </h1>
            </div>
        </div>
        );
}
