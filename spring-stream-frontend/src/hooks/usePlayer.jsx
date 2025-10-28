import { useState } from 'react';

export const usePlayer = () => {
  const [videoId, setVideoId] = useState('f6e21144-c462-459b-af96-1cde95621710');
  const [searchInput, setSearchInput] = useState('');

  const handlePlayVideo = () => {
    if (searchInput.trim()) {
      setVideoId(searchInput);
      setSearchInput('');
    }
  };

  return { videoId, searchInput, setSearchInput, handlePlayVideo };
};