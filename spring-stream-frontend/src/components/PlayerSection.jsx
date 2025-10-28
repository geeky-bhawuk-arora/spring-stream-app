import { Play } from 'lucide-react';

export default function PlayerSection({ videoId }) {
  return (
    <div className="space-y-6">
      <div>
        <h2 className="text-lg font-semibold text-gray-900 mb-3">Video Player</h2>
        <div className="bg-black rounded-lg overflow-hidden aspect-video flex items-center justify-center">
          <div className="text-center">
            <Play className="w-16 h-16 text-gray-600 mx-auto mb-2" />
            <p className="text-gray-400 text-sm">Playing: {videoId}</p>
          </div>
        </div>
        <p className="text-xs text-gray-500 mt-2">
          Endpoint: /api/v1/videos/{videoId}/master.m3u8
        </p>
      </div>
    </div>
  );
}