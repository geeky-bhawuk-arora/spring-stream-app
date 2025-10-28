import React, {  useRef } from 'react';
import Header from './components/Header';
import PlayerSection from './components/PlayerSection';
import UploadSection from './components/UploadSection';
import SearchSection from './components/SearchSection';
import { useUpload } from './hooks/useUpload';
import { usePlayer } from './hooks/usePlayer';

export default function App() {
  const {
    uploadData,
    setUploadData,
    uploading,
    uploadProgress,
    message,
    handleFileSelect,
    handleUpload,
  } = useUpload();

  const { videoId, searchInput, setSearchInput, handlePlayVideo } = usePlayer();

  const fileInputRef = useRef(null);

  return (
    <div className="min-h-screen bg-gray-50">
      <Header />
      <div className="max-w-7xl mx-auto px-6 py-8">
        <div className="grid grid-cols-1 lg:grid-cols-2 gap-8">
          <div className="space-y-6">
            <PlayerSection videoId={videoId} />
            <SearchSection
              searchInput={searchInput}
              setSearchInput={setSearchInput}
              onPlayVideo={handlePlayVideo}
            />
          </div>

          <UploadSection
            uploadData={uploadData}
            setUploadData={setUploadData}
            uploading={uploading}
            uploadProgress={uploadProgress}
            message={message}
            fileInputRef={fileInputRef}
            onFileSelect={handleFileSelect}
            onUpload={handleUpload}
          />
        </div>
      </div>
    </div>
  );
}