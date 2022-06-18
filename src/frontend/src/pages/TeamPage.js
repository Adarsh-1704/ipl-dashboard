import {MatchDetailCard} from '../components/MatchDetailCard';
import {MatchSmallCard} from '../components/MatchSmallCard';
import {React,useEffect,useState} from 'react';
import {useParams,Link} from 'react-router-dom';
import './TeamPage.scss';
import {PieChart} from 'react-minimal-pie-chart';

export function TeamPage() {
    const [team,setTeam] = useState({});
    const {teamName} = useParams();
    useEffect(
    ()=>{
        const getTeamData = async()=>{
            const response = await fetch(`/team/teamName/${teamName}`);
            const data = await response.json();
            setTeam(data);
        }

        getTeamData();
    },[teamName]
    )

    if(!team.teamName)
    return (
        <h1>Team Not Found!!!</h1>
        );

    else
    return (
        <div className="TeamPage">
            <div className="team-name-section">
              <h2 className="team-name">{team.teamName}</h2>
              <img key={teamName} src={require(`../logo/${teamName}.png`)} width="190" height="100" alt="team-logo" />
            </div>

            <div className="win-loss-section">
                <PieChart data={[
                    { title: 'Losses', value: team.totalMatches - team.totalWins, color: '#660000' },
                    { title: 'Wins', value: team.totalWins, color: '#003300' }]}/>
            </div>

            <div className="match-detail-section">
                <MatchDetailCard teamName ={team.teamName} match={team.matchList[0]}/>
            </div>

                {team.matchList.slice(1).map(matObj=><MatchSmallCard key={matObj.id} teamName={team.teamName} match={matObj} />)}

            <div className="more-link">
                <Link to={`/team/${teamName}/year/2008`}>More</Link>
            </div>

            <div className="home-link">
                <Link to={`/`}>Home</Link>
            </div>
        </div>
        );
}
