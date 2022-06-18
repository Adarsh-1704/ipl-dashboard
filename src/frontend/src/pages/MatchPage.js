import {MatchDetailCard} from '../components/MatchDetailCard';
import {React,useEffect,useState} from 'react';
import {useParams,Link} from 'react-router-dom';
import './MatchPage.scss';
import {YearSelector} from '../components/YearSelector';

export const MatchPage = () => {

    const [matches,setMatches]=useState([]);
    const {teamName,year}=useParams();

    useEffect(
    ()=>{
        const getMatches = async() => {

        const response = await fetch(`/team/${teamName}?year=${year}`);

        const data = await response.json();

        setMatches(data);
        }

        getMatches();

    },
    [teamName,year]
    )

  return (
    <div className="MatchPage">

        <div className="year-selector">
            <YearSelector teamName={teamName} />
        </div>
        <div>
            <h2 className="page-heading">List of Matches of {teamName} in Year {year}</h2>
            {matches.map(matObj => <MatchDetailCard key={matObj.id} teamName={teamName} match={matObj} />)}
        </div>
        <div>
            <Link to={`/`}>Home</Link>
        </div>
    </div>
  );

}
